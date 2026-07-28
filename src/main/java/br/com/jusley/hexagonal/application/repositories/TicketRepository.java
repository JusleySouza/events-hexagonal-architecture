package br.com.jusley.hexagonal.application.repositories;

import br.com.jusley.hexagonal.application.domain.event.ticket.Ticket;
import br.com.jusley.hexagonal.application.domain.event.ticket.TicketId;
import java.util.Optional;

//repositorio do agregado
public interface TicketRepository {

    Optional<Ticket> ticketOfId(TicketId anId);

    Ticket create(Ticket ticket);

    Ticket update(Ticket ticket);

    void deleteAll();
}
