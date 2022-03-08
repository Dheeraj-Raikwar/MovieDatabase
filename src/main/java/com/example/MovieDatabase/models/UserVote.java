package com.example.MovieDatabase.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_voting")
public class UserVote {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "voting_id")
	private long voting_id;

	@Column(name = "user_id")
	private long user_id;	
	
	
	public UserVote(long user_id) {
		super();
		this.user_id = user_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getVoting_id() {
		return voting_id;
	}

	public void setVoting_id(long voting_id) {
		this.voting_id = voting_id;
	}


}
