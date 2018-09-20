package pl.szarawara.jakub.SqlTables;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ScreenRoom {

	public ScreenRoom(){

	}

	public ScreenRoom(int screenRoomRows, int screenRoomColumns){
		this.screenRoomRows = screenRoomRows;
		this.screenRoomColumns = screenRoomColumns;
	}

	@Id
	@GeneratedValue
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getScreenRoomRows() {
		return screenRoomRows;
	}

	public int getScreenRoomColumns() {
		return screenRoomColumns;
	}

	@OneToMany(mappedBy="screenRoom")
	private List<Displays> displays;

	private int screenRoomRows;
	private int screenRoomColumns;


}
