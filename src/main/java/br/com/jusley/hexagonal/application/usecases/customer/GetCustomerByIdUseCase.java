package br.com.jusley.hexagonal.application.usecases.customer;

import br.com.jusley.hexagonal.application.usecases.UseCase;
import br.com.jusley.hexagonal.application.domain.customer.CustomerId;
import br.com.jusley.hexagonal.application.repositories.CustomerRepository;
import java.util.Objects;
import java.util.Optional;

public class GetCustomerByIdUseCase extends UseCase<GetCustomerByIdUseCase.Input, Optional<GetCustomerByIdUseCase.Output>> {
    private final CustomerRepository customerRepository;

    public GetCustomerByIdUseCase(final CustomerRepository customerRepository) {
        this.customerRepository = Objects.requireNonNull(customerRepository);
    }

    @Override
    public Optional<Output> execute(final Input input) {
        return customerRepository.customerOfId(CustomerId.with(input.id()))
                .map(customer -> new Output(
                        customer.customerId().value(),
                        customer.name().value(),
                        customer.cpf().value(),
                        customer.email().value()
                ));
    }

    public record Input(String id){}

   public record Output(String id, String name, String cpf, String email){}
}
