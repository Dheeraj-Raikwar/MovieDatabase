package com.example.MovieDatabase.models;


import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "release_date")
	private Date releaseDate;
	
	@Column(name = "score")
	private Float score;
	
	public Movie(String title, String genre, String details, Date releaseDate, Float score) {
		super();
		this.title = title;
		this.genre = genre;
		this.details = details;
		this.releaseDate = releaseDate;
		this.score = score;
	}

	public Movie() {}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public String getDetails() {
		return details;
	}

	public String getReleaseDate() {
		return String.format("%1$td %1$tB %1$tY ", releaseDate);
	}
	
	
}
