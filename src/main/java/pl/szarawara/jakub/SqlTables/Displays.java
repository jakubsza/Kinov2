package pl.szarawara.jakub.SqlTables;

import pl.szarawara.jakub.SqlTables.impl.SqlTableImpl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Displays implements SqlTableImpl{

    public Displays() {

    }

    public Displays(Calendar day, Calendar hour, ScreenRoom screenRoom) {
        this.day = day.getTime().toLocaleString().substring(0, 10);
        this.hour = hour.getTime().toString().substring(11, 19);
        this.screenRoom = screenRoom;
    }

    @Id
    @GeneratedValue
    private long id;

    private String day;
    private String hour;

    @ManyToOne
    @JoinColumn(name = "sala_ID")
    private ScreenRoom screenRoom;

    private int freeSeats;

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    @OneToMany
    @JoinColumn(name = "wyswietleniaID")
    private List<Seats> seats;

    public ScreenRoom getScreenRoom() {
        return screenRoom;
    }

    public void setScreenRoom(ScreenRoom screenRoom) {
        this.screenRoom = screenRoom;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Seats> getSeats() {
        return seats;
    }

    public void setSeats(List<Seats> seats) {
        this.seats = seats;
    }

    public String getSpecification(){return null;}

}
