package pl.szarawara.jakub.SqlTables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employees {

    public Employees() {

    }

    public Employees(String numberID, String password) {
        this.numberID = numberID;
        this.password = password;
    }

    @Id
    @GeneratedValue
    private long id;

    private String numberID;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumberID() {
        return numberID;
    }

    public void setNumberID(String numberID) {
        this.numberID = numberID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
