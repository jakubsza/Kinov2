package pl.szarawara.jakub.SqlTables;

import pl.szarawara.jakub.SqlTables.impl.SqlTableImpl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Discounts implements SqlTableImpl{

    public Discounts() {

    }

    public Discounts(String code, double discount) {
        this.code = code;
        this.discount = discount;
    }

    @Id
    @GeneratedValue
    private long id;

    private String code;
    private double discount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpecification() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


}
