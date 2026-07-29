package br.com.jusley.hexagonal.infrastructure.graphql;

import br.com.jusley.hexagonal.application.usecases.event.CreateEventUseCase;
import br.com.jusley.hexagonal.application.usecases.event.SubscribeCustomerToEventUseCase;
import br.com.jusley.hexagonal.infrastructure.dtos.NewEventDTO;
import br.com.jusley.hexagonal.infrastructure.dtos.SubscribeDTO;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

//url: http://localhost:8080/graphql

//Adapter para o graphql
@Controller
public class EventResolver {

    private final CreateEventUseCase createEventUseCase;

    private final SubscribeCustomerToEventUseCase subscribeCustomerToEventUseCase;

    public EventResolver(
            final CreateEventUseCase createEventUseCase,
            final SubscribeCustomerToEventUseCase subscribeCustomerToEventUseCase) {
        this.createEventUseCase = Objects.requireNonNull(createEventUseCase);
        this.subscribeCustomerToEventUseCase = Objects.requireNonNull(subscribeCustomerToEventUseCase);
    }

    @MutationMapping
    public CreateEventUseCase.Output createEvent(@Argument NewEventDTO input) {
        return createEventUseCase.execute(new CreateEventUseCase.Input(input.name(), input.date(), input.totalSpots(), input.partnerId()));
    }

    @Transactional
    @MutationMapping
    public SubscribeCustomerToEventUseCase.Output subscribeCustomerToEvent(@Argument SubscribeDTO input) {
        return subscribeCustomerToEventUseCase.execute(new SubscribeCustomerToEventUseCase.Input(input.eventId(), input.customerId()));
    }

}
