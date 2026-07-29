package br.com.jusley.hexagonal.application.usecases.event;

import br.com.jusley.hexagonal.application.usecases.UseCase;
import br.com.jusley.hexagonal.application.domain.event.Event;
import br.com.jusley.hexagonal.application.domain.partner.PartnerId;
import br.com.jusley.hexagonal.application.exceptions.ValidationException;
import br.com.jusley.hexagonal.application.repositories.EventRepository;
import br.com.jusley.hexagonal.application.repositories.PartnerRepository;
import java.util.Objects;

public class CreateEventUseCase extends UseCase <CreateEventUseCase.Input, CreateEventUseCase.Output> {

    private final EventRepository eventRepository;
    private final PartnerRepository partnerRepository;

    public CreateEventUseCase(final EventRepository eventRepository, final PartnerRepository partnerRepository) {
        this.eventRepository = Objects.requireNonNull(eventRepository);
        this.partnerRepository = Objects.requireNonNull(partnerRepository);
    }

    @Override
    public Output execute(final Input input) {
        final var aPartner =  partnerRepository.partnerOfId(PartnerId.with(input.partnerId))
                .orElseThrow(() -> {
                    return new ValidationException("Partner not found");
                });

        final var anEvent = eventRepository.create(Event.newEvent(input.name, input.date, input.totalSpots, aPartner));
        return new Output(
                anEvent.eventId().value(),
                anEvent.name().value(),
                input.date,
                anEvent.totalSpots(),
                anEvent.partnerId().value()
        );
    }

    public record Input(String name, String date, Integer totalSpots, String partnerId) {}

    public record Output(String id, String name, String date, Integer totalSpots, String partnerId) {}
}
