package com.example.MovieDatabase.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.MovieDatabase.models.Reviews;

public interface ReviewsRepository extends JpaRepository<Reviews, Long>{
	
	// Fetch records by review_id
	@Query(value ="select count(id)>0 from review where review_id= ?1",nativeQuery=true)
	boolean existsByReviewId(Long reviewId);
	
	// Fetch records by review-id movie_id
	@Query(value ="select count(id)>0 from review where review_id= ?1 AND movie_id=?2",nativeQuery=true)
	boolean existsByReviewMovieId(long reviewId, long movieId);
	
	//SET Review by review_id
	@Modifying
	@Transactional
	@Query(value ="update review set message=?3 where review_id= ?1 AND movie_id=?2",nativeQuery=true) 
	void addReviewByReviewId(long reviewId, long movieId, String msg);
	
	// Insert new Record
	@Modifying
	@Transactional
	@Query(value ="insert into review(review_id,movie_id,message) values(?1,?2,?3)",nativeQuery=true)
	void InsertNew(Long reviewid,Long movieid, String msg);

}
