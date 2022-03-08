package com.example.MovieDatabase.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Reviews {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "review_id")
	private long review_id;
	
	@Column(name = "movie_id")
	private long movie_id;
	
	@Column(name = "message")
	private String message;

	public Reviews() {
		super();
	}
	
	public Reviews(long vote_id, long movie_id, String message) {
		super();
		this.review_id = vote_id;
		this.movie_id = movie_id;
		this.message = message;
	}

	public long getReview_id() {
		return review_id;
	}

	public void setReview_id(long review_id) {
		this.review_id = review_id;
	}

	public long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(long movie_id) {
		this.movie_id = movie_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
