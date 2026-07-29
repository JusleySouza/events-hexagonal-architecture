package br.com.jusley.hexagonal.infrastructure.jpa.repositories;

import br.com.jusley.hexagonal.infrastructure.jpa.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.UUID;

public interface CustomerJpaRepository extends CrudRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findByCpf(String cpf);

    Optional<CustomerEntity> findByEmail(String email);
}
