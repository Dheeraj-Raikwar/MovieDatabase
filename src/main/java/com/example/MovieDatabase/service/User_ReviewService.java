package com.example.MovieDatabase.service;

import com.example.MovieDatabase.models.UserReview;
public interface User_ReviewService {
	
	  //Find vote by user_id
	  long findByUserId(Long id);
		 
      //Insert new record
	  void InsertNew(long id,long vote_id);

}
