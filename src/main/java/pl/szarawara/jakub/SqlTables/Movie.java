package pl.szarawara.jakub.SqlTables;

import pl.szarawara.jakub.SqlTables.impl.SqlTableImpl;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Movie implements SqlTableImpl{

	public Movie(){

	}

	public Movie(String title, int movieDuration, int movieAgeLimit){
		this.title = title;
		this.movieDuration = movieDuration;
		this.movieAgeLimit = movieAgeLimit;
	}

	public Movie(String title, int movieDuration, int movieAgeLimit, List<Displays> displays){
		this.displays = displays;
		this.title = title;
		this.movieDuration = movieDuration;
		this.movieAgeLimit = movieAgeLimit;
	}

	@Id
	@GeneratedValue
	private long id;

	@OneToMany
	@JoinColumn(name="Film_ID")
	private List<Displays> displays;

	public List<Displays> getDisplays() {
		return displays;
	}

	public void setDisplays(List<Displays> displays) {
		this.displays = displays;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String title;
	private int movieDuration;
	private int movieAgeLimit;
	public String getSpecification() {
		return title;
	}

	public int getMovieDuration() {
		return movieDuration;
	}

	public int getMovieAgeLimit() {
		return movieAgeLimit;
	}


}
