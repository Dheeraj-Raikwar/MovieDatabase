package com.example.MovieDatabase.service;

import com.example.MovieDatabase.models.Vote;
public interface VoteService {
	
	//Check if vote-Id exists    
	boolean existsByVoteId(long id);
	
	//Add Vote
	void addVoteByUserId(long userId,long movieId,int vote);
	
}
