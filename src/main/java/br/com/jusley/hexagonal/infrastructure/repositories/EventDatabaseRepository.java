package br.com.jusley.hexagonal.infrastructure.repositories;

import br.com.jusley.hexagonal.application.domain.event.Event;
import br.com.jusley.hexagonal.application.domain.event.EventId;
import br.com.jusley.hexagonal.application.repositories.EventRepository;
import br.com.jusley.hexagonal.infrastructure.jpa.entities.EventEntity;
import br.com.jusley.hexagonal.infrastructure.jpa.repositories.EventJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

//Interface Adapter
@Component
public class EventDatabaseRepository implements EventRepository {

    private final EventJpaRepository eventJpaRepository;

    public EventDatabaseRepository(final EventJpaRepository eventJpaRepository) {
        this.eventJpaRepository = Objects.requireNonNull(eventJpaRepository);
    }

    @Override
    public Optional<Event> eventOfId(final EventId anId) {
        Objects.requireNonNull(anId, "Id cannot be null");
        return this.eventJpaRepository.findById(UUID.fromString(anId.value()))
                .map(EventEntity:: toEvent);
    }

    @Override
    @Transactional
    public Event create(Event event) {
        return this.eventJpaRepository.save(EventEntity.of(event)).toEvent();
    }

    @Override
    @Transactional
    public Event update(Event event) {
        return this.eventJpaRepository.save(EventEntity.of(event)).toEvent();
    }

    @Override
    public void deleteAll() {
        this.eventJpaRepository.deleteAll();
    }
}
