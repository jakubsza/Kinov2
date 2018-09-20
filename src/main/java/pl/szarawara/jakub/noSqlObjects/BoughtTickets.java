package pl.szarawara.jakub.noSqlObjects;

public class BoughtTickets {

    private double totalPrice;
    private int totalBoughtTicketsNumber;

    public int getTotalBoughtTicketsNumber() {
        return totalBoughtTicketsNumber;
    }

    public void setTotalBoughtTicketsNumber(int totalBoughtTicketsNumber) {
        this.totalBoughtTicketsNumber = totalBoughtTicketsNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
