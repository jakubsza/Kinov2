package pl.szarawara.jakub.SqlTables;

import pl.szarawara.jakub.SqlTables.impl.SqlTableImpl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tickets implements SqlTableImpl{

    public Tickets() {

    }

    public Tickets(String ticketKind, double ticketPrice) {
        this.ticketKind = ticketKind;
        this.ticketPrice = ticketPrice;
    }

    @Id
    @GeneratedValue
    private long id;

    private String ticketKind;
    private double ticketPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpecification() {
        return ticketKind;
    }

    public void setTicketKind(String ticketKind) {
        this.ticketKind = ticketKind;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }


}
