package br.com.jusley.hexagonal.application.repositories;

import br.com.jusley.hexagonal.application.domain.event.Event;
import br.com.jusley.hexagonal.application.domain.event.EventId;
import java.util.Optional;

//repositorio do agregado
public interface EventRepository {

    Optional<Event> eventOfId(EventId anId);

    Event create(Event event);

    Event update(Event event);

    void deleteAll();
}
