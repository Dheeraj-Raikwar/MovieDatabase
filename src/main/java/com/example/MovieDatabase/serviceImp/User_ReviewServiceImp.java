package com.example.MovieDatabase.serviceImp;

import org.springframework.stereotype.Service;
import com.example.MovieDatabase.repository.User_ReviewRepository;
import com.example.MovieDatabase.service.User_ReviewService;

@Service
public class User_ReviewServiceImp implements User_ReviewService{
	
	private User_ReviewRepository user_ReviewRepository;
	
	
	public User_ReviewServiceImp(User_ReviewRepository user_ReviewRepository) {
		super();
		this.user_ReviewRepository = user_ReviewRepository;
	}

	@Override
	public long findByUserId(Long id) {
		return user_ReviewRepository.findByUserId(id);
	}

	@Override
	public void InsertNew(long id, long vote_id) {
		user_ReviewRepository.InsertNew(id,vote_id);
	}

}
