package com.example.MovieDatabase.serviceImp;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import com.example.MovieDatabase.models.Reviews;
import com.example.MovieDatabase.repository.ReviewsRepository;
import com.example.MovieDatabase.repository.User_ReviewRepository;
import com.example.MovieDatabase.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	
    private ReviewsRepository reviewsRepository;
	
	private User_ReviewRepository user_ReviewRepository;
	
	
	public ReviewServiceImpl(ReviewsRepository reviewsRepository, User_ReviewRepository user_ReviewRepository) {
		super();
		this.reviewsRepository = reviewsRepository;
		this.user_ReviewRepository = user_ReviewRepository;
	}

	@Override
	public boolean existsByReviewId(long id) {
		
		return reviewsRepository.existsByReviewId(id);
		
	}

	@Override
	public void addReviewByUserId(long user_id, long movie_id, String msg) {
		Long review_id = user_ReviewRepository.findByUserId(user_id);
		boolean isReviewIdPresent =false;
		
		if(review_id!=null)	
		isReviewIdPresent = reviewsRepository.existsByReviewId(review_id);
		
		//If Vote-id is null or not Present
		if(review_id==null || !isReviewIdPresent) {
				while(true) {
					
					//Generate New Vote Id
					Random rd = new Random();
				    long generatedReview_id = (long)((rd.nextLong() * ((10L - 1) + 1)));
					boolean isPresent =false;
					isPresent = reviewsRepository.existsByReviewId(generatedReview_id);
					if(isPresent || generatedReview_id<0)
					isPresent=true;
					
					if(!isPresent) {
						
						//Insert new review-id In User-Review table
						user_ReviewRepository.InsertNew(generatedReview_id,user_id);
						
						//Add Review in Review Table
						reviewsRepository.InsertNew(generatedReview_id, movie_id,msg );
						
						break;
						
					}
				}
			}
		
		else {
			//If movie-id present 
			boolean isPresent=false;
			
			isPresent = reviewsRepository.existsByReviewMovieId(review_id,movie_id);
			
			if(isPresent){
				reviewsRepository.addReviewByReviewId(review_id, movie_id, msg);
		
			}
			else {
				reviewsRepository.InsertNew(review_id, movie_id, msg);
			}
		}
		
	}
		
}

