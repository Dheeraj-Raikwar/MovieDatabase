package com.example.MovieDatabase.Request;

import java.util.Date;

public class AddMovie {
	
	private String title;
	private String genre;
	private String details;

	private Date release_date;
	
	public String getTitle() {
		return title;
	}
	public String getGenre() {
		return genre;
	}
	public String getDetails() {
		return details;
	}
	public Date getRelease_date() {
		return release_date;
	}
	
	
}
