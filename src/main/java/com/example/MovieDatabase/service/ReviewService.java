package com.example.MovieDatabase.service;
import com.example.MovieDatabase.models.Reviews;
public interface ReviewService {
	
	//Check if review-Id exists    
	boolean existsByReviewId(long id);
		
	//Add review
	void addReviewByUserId(long user_id, long movie_id, String msg);
		

}
