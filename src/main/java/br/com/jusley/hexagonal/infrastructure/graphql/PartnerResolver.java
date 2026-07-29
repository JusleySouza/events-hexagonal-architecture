package br.com.jusley.hexagonal.infrastructure.graphql;

import br.com.jusley.hexagonal.application.usecases.partner.CreatePartnerUseCase;
import br.com.jusley.hexagonal.application.usecases.partner.GetPartnerByIdUseCase;
import br.com.jusley.hexagonal.infrastructure.dtos.NewPartnerDTO;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.Objects;

//url: http://localhost:8080/graphql

//Adapter para o graphql
@Controller
public class PartnerResolver {

    private final CreatePartnerUseCase createPartnerUseCase;
    private final GetPartnerByIdUseCase getPartnerByIdUseCase;

    public PartnerResolver(
            final CreatePartnerUseCase createPartnerUseCase,
            final GetPartnerByIdUseCase getPartnerByIdUseCase) {
        this.createPartnerUseCase = Objects.requireNonNull(createPartnerUseCase);
        this.getPartnerByIdUseCase = Objects.requireNonNull(getPartnerByIdUseCase);
    }

    @MutationMapping
    public CreatePartnerUseCase.Output createPartner(@Argument NewPartnerDTO input) {
            return createPartnerUseCase.execute(new CreatePartnerUseCase.Input(input.name(), input.cnpj(), input.email()));
    }

    @QueryMapping
    public GetPartnerByIdUseCase.Output partnerOfId(@Argument String id) {
        return getPartnerByIdUseCase.execute(new GetPartnerByIdUseCase.Input(id)).orElse(null);
    }

}
