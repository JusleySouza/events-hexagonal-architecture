package br.com.jusley.hexagonal.infrastructure.graphql;

import br.com.jusley.hexagonal.application.usecases.customer.CreateCustomerUseCase;
import br.com.jusley.hexagonal.application.usecases.customer.GetCustomerByIdUseCase;
import br.com.jusley.hexagonal.infrastructure.dtos.NewCustomerDTO;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.Objects;

//url: http://localhost:8080/graphql

//Adapter para o graphql
@Controller
public class CustomerResolver {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;

    public CustomerResolver(
            final CreateCustomerUseCase createCustomerUseCase,
            final GetCustomerByIdUseCase getCustomerByIdUseCase) {
        this.createCustomerUseCase = Objects.requireNonNull(createCustomerUseCase);
        this.getCustomerByIdUseCase = Objects.requireNonNull(getCustomerByIdUseCase);
    }

    @MutationMapping
    public CreateCustomerUseCase.Output createCustomer(@Argument NewCustomerDTO input) {
            return createCustomerUseCase.execute(new CreateCustomerUseCase.Input(input.name(), input.cpf(), input.email()));
    }

    @QueryMapping
    public GetCustomerByIdUseCase.Output customerOfId(@Argument String id) {
        return getCustomerByIdUseCase.execute(new GetCustomerByIdUseCase.Input(id)).orElse(null);
    }

}
