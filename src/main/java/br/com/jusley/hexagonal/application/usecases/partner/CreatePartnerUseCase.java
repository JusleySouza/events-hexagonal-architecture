package br.com.jusley.hexagonal.application.usecases.partner;

import br.com.jusley.hexagonal.application.domain.person.Cnpj;
import br.com.jusley.hexagonal.application.domain.person.Email;
import br.com.jusley.hexagonal.application.usecases.UseCase;
import br.com.jusley.hexagonal.application.domain.partner.Partner;
import br.com.jusley.hexagonal.application.repositories.PartnerRepository;
import br.com.jusley.hexagonal.application.exceptions.ValidationException;
import java.util.Objects;

public class CreatePartnerUseCase extends UseCase<CreatePartnerUseCase.Input, CreatePartnerUseCase.Output> {
    private final PartnerRepository partnerRepository;

    public CreatePartnerUseCase(final PartnerRepository partnerRepository) {
        this.partnerRepository = Objects.requireNonNull(partnerRepository);
    }

    @Override
    public Output execute(final Input input) {
        if (partnerRepository.partnerOfCNPJ(new Cnpj(input.cnpj)).isPresent()) {
            throw new ValidationException("Partner already exists");
        }
        if (partnerRepository.partnerOfEmail(new Email(input.email)).isPresent()) {
            throw new ValidationException("Partner already exists");
        }

        var partner = partnerRepository.create(Partner.newPartner(input.name(), input.cnpj(), input.email()));

        return new Output(
                partner.partnerId().value(),
                partner.name().value(),
                partner.cnpj().value(),
                partner.email().value());
    }

    public record Input(String name, String cnpj, String email){}

    public record Output(String id, String name, String cnpj, String email){}

}
