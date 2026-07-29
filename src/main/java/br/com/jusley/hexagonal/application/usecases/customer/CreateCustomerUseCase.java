package br.com.jusley.hexagonal.application.usecases.customer;

import br.com.jusley.hexagonal.application.domain.person.Cpf;
import br.com.jusley.hexagonal.application.domain.person.Email;
import br.com.jusley.hexagonal.application.usecases.UseCase;
import br.com.jusley.hexagonal.application.exceptions.ValidationException;
import br.com.jusley.hexagonal.application.repositories.CustomerRepository;
import br.com.jusley.hexagonal.application.domain.customer.Customer;

public class CreateCustomerUseCase extends UseCase <CreateCustomerUseCase.Input, CreateCustomerUseCase.Output> {

    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Output execute(final Input input) {

        if (customerRepository.customerOfCPF(new Cpf(input.cpf())).isPresent()) {
            throw new ValidationException("Customer already exists");
        }
        if (customerRepository.customerOfEmail(new Email(input.email())).isPresent()) {
            throw new ValidationException("Customer already exists");
        }

        var customer = customerRepository.create(Customer.newCustomer(input.name, input.cpf, input.email));

        return new Output(
                customer.customerId().value(),
                customer.name().value(),
                customer.cpf().value(),
                customer.email().value());
    }

    public record Input(String name, String cpf, String email) {}

    public record Output(String id, String name, String cpf, String email) {}
}
