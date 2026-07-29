package br.com.jusley.hexagonal.application.usecases.partner;

import br.com.jusley.hexagonal.application.usecases.UseCase;
import br.com.jusley.hexagonal.application.domain.partner.PartnerId;
import br.com.jusley.hexagonal.application.repositories.PartnerRepository;
import java.util.Objects;
import java.util.Optional;

public class GetPartnerByIdUseCase extends UseCase<GetPartnerByIdUseCase.Input, Optional<GetPartnerByIdUseCase.Output>> {
    private final PartnerRepository partnerRepository;

    public GetPartnerByIdUseCase(final PartnerRepository partnerRepository) {
        this.partnerRepository = Objects.requireNonNull(partnerRepository);
    }

    @Override
    public Optional<Output> execute(final Input input) {
        return partnerRepository.partnerOfId(PartnerId.with(input.id()))
                .map(partner -> new Output(
                        partner.partnerId().value(),
                        partner.name().value(),
                        partner.cnpj().value(),
                        partner.email().value()
                ));
    }

    public record Input(String id){}

    public record Output(String id, String name, String cnpj, String email){}
}
