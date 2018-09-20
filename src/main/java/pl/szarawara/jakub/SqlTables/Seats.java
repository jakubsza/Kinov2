package pl.szarawara.jakub.SqlTables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Seats {

	public Seats(){

	}

	public Seats(int seatRow, int seatColumn){
		this.seatRow = seatRow;
		this.seatColumn = seatColumn;
		this.occupied =false;
	}

	@Id
	@GeneratedValue
	private long id;

	private int seatRow;
	private int seatColumn;
	private boolean occupied;


	public int getSeatRow() {
		return seatRow;
	}
	public int getSeatColumn() {
		return seatColumn;
	}

	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
	public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}



}