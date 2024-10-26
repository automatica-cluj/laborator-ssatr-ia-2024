package utcluj.aut.demoticketsserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utcluj.aut.demoticketsserver.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}