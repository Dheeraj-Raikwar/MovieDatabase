package com.example.MovieDatabase.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="favourite_genre")
public class Favourite_genre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "genre")
	private String genre;
	
	public Favourite_genre(long id, String genre) {
		super();
		this.id = id;
		this.genre = genre;
	}

	public long getUser_id() {
		return id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}


}
