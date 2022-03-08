package com.example.MovieDatabase.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_review")
public class UserReview {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "review_id")
	private long review_id;

	
	@Column(name = "user_id")
	private long user_id;
	

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getReview_id() {
		return review_id;
	}

	public void setReview_id(long review_id) {
		this.review_id = review_id;
	}
	
	
	

}
