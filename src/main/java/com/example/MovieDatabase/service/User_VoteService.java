package com.example.MovieDatabase.service;

import com.example.MovieDatabase.models.UserVote;
public interface User_VoteService {
	
	
	//Check if Vote-Id exists    
	boolean existsById(Long id);
	
	//Find vote by user_id
	long findByUserId(Long id);
	 
	//Insert new record
    void InsertNew(long id,long vote_id);
     

}