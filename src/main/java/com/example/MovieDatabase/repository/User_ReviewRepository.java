package com.example.MovieDatabase.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.MovieDatabase.models.UserReview;

public interface User_ReviewRepository extends JpaRepository<UserReview, Long>{
	
	// Fetch records by user_id
	@Query(value ="select * from user_review where user_id= ?1",nativeQuery=true)
	Optional<UserReview> existsByUserId(Long userId);
	  
	boolean existsById(Long id);
	
	// Fetch review_id by user_id
	@Query(value ="select review_id from user_review where user_id= ?1",nativeQuery=true)
	Long findByUserId(Long id);
			
	// Insert new Record
	@Modifying
	@Transactional
	@Query(value ="insert into user_review (review_id,user_id) values(?1,?2)",nativeQuery=true)
	void InsertNew(Long review_id,Long user_id);

}
