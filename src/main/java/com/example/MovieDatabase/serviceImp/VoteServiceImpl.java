package com.example.MovieDatabase.serviceImp;

import com.example.MovieDatabase.repository.MovieRepository;
import com.example.MovieDatabase.repository.User_VoteRepository;
import com.example.MovieDatabase.repository.VoteRepository;
import com.example.MovieDatabase.service.MovieService;
import com.example.MovieDatabase.service.VoteService;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.MovieDatabase.models.Vote;

@Service
public class VoteServiceImpl implements VoteService{
	
	private VoteRepository voteRepository;
	
	private User_VoteRepository user_VoteRepository;
	
	private MovieService movieService;
	
	public VoteServiceImpl(VoteRepository voteRepository, User_VoteRepository user_VoteRepository, MovieService movieService) {
		super();
		this.voteRepository = voteRepository;
		this.user_VoteRepository = user_VoteRepository;
		this.movieService = movieService;
	}

	@Override
	public boolean existsByVoteId(long id) {
		
		return voteRepository.existsByVoteId(id);
	}

	@Override
	public void addVoteByUserId(long user_id,long movie_id,int vote){
		
		Long vote_id = user_VoteRepository.findByUserId(user_id);
		boolean isVoteIdPresent =false;
		
		if(vote_id!=null)
		isVoteIdPresent = voteRepository.existsByVoteId(vote_id);
		
		//If Vote-id is null or not Present
		if(vote_id==null || !isVoteIdPresent) {
				while(true) {
					
					//Generate New Vote Id
					Random rd = new Random();
				    long generatedVote_id = (long)((rd.nextLong() * ((10L - 1) + 1)));
					boolean isPresent = false;
					isPresent = voteRepository.existsByVoteId(generatedVote_id);
					if(isPresent || generatedVote_id<0)
					isPresent=true;
					
					if(!isPresent) {
						
						//Insert new vote-id In User-Vote table
						user_VoteRepository.InsertNew(generatedVote_id,user_id);
						
						//Add Vote in voting Table
						voteRepository.InsertNew(generatedVote_id, movie_id, vote);
						
						//Update Score in Movie Table
						movieService.UpdateMovieScore(movie_id);
						
						break;
						
					}
				}
			}
		
		else {
			
			//If movie-id present 
			boolean isPresent=false;
			
			isPresent = voteRepository.existsByVoteMovieId(vote_id,movie_id);
			
			if(isPresent){
				voteRepository.addVoteByVoteId(vote_id, movie_id, vote);
				//Update Score in Movie Table
				movieService.UpdateMovieScore(movie_id);
			}
			else {
				voteRepository.InsertNew(vote_id, movie_id, vote);
				//Update Score in Movie Table
				movieService.UpdateMovieScore(movie_id);
			}
				
		}
		
	}
	

}
