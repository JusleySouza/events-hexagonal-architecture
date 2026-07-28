package br.com.jusley.hexagonal.application.repositories;

import br.com.jusley.hexagonal.application.domain.customer.Customer;
import br.com.jusley.hexagonal.application.domain.customer.CustomerId;
import br.com.jusley.hexagonal.application.domain.person.Cpf;
import br.com.jusley.hexagonal.application.domain.person.Email;
import java.util.Optional;

//repositorio do agregado
public interface CustomerRepository {

    Optional<Customer> customerOfId(CustomerId anId);

    Optional<Customer> customerOfCPF(Cpf cpf);

    Optional<Customer> customerOfEmail(Email email);

    Customer create(Customer customer);

    Customer update(Customer customer);

    void deleteAll();
}
