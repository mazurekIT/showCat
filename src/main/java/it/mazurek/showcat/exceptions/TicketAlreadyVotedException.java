package it.mazurek.showcat.exceptions;

public class TicketAlreadyVotedException extends RuntimeException {
    public TicketAlreadyVotedException(Long id) {
        super("Ticket id: " + id + " already voted!");
    }
}
