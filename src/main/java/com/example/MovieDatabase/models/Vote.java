package com.example.MovieDatabase.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="voting")
public class Vote {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "movie_id")
	private long movie_id;
	
	@Column(name = "vote")
	private int vote;
	
	@Column(name = "vote_id")
	private long vote_id;

	public Vote() {
		super();
	}

	public Vote(long movie_id, int vote, long vote_id) {
		super();
		this.movie_id = movie_id;
		this.vote = vote;
		this.vote_id = vote_id;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public long getVote_id() {
		return vote_id;
	}

	public void setVote_id(long vote_id) {
		this.vote_id = vote_id;
	}

	public long getMovie_id() {
		return movie_id;
	}
	
	

}
