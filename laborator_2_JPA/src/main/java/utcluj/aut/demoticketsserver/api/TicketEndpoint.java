package utcluj.aut.demoticketsserver.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import utcluj.aut.demoticketsserver.entity.Ticket;
import utcluj.aut.demoticketsserver.service.TicketService;

import java.util.List;

@RestController

public class TicketEndpoint {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/tickets")
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    @PostMapping("/others")
    public Ticket addTicket2(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

}
