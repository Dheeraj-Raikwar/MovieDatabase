package com.example.MovieDatabase.serviceImp;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.MovieDatabase.repository.User_VoteRepository;
import com.example.MovieDatabase.service.User_VoteService;
import com.example.MovieDatabase.models.UserVote;

@Service
public class User_VoteServiceImp implements User_VoteService{
	
	private User_VoteRepository user_VoteRepository;
	

	public User_VoteServiceImp(User_VoteRepository user_VoteRepository) {
		super();
		this.user_VoteRepository = user_VoteRepository;
	}


	@Override
	public long findByUserId(Long id) {
		return user_VoteRepository.findByUserId(id);
	}

	
	@Override
	public void InsertNew(long id, long vote_id) {
		user_VoteRepository.InsertNew(id,vote_id);
	}

	@Override
	public boolean existsById(Long id) {
		Optional<UserVote> userVote = user_VoteRepository.existsByUserId(id);
		if(userVote.isPresent())
		return true;
		return false;
	}

	
}
