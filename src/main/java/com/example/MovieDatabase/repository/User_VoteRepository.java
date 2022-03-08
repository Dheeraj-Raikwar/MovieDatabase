package com.example.MovieDatabase.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.MovieDatabase.models.UserVote;

public interface User_VoteRepository extends JpaRepository<UserVote, Long>{

	// Fetch records by user_id
	@Query(value ="select * from Voting where user_id= ?1",nativeQuery=true)
	Optional<UserVote> existsByUserId(Long userId);
	
	
	boolean existsById(Long voteId);
	
	// Fetch vote_id by user_id
	@Query(value ="select voting_id from user_voting where user_id= ?1",nativeQuery=true)
	Long findByUserId(Long id);
		
	// Insert new Record
	@Modifying
	@Transactional
	@Query(value ="insert into user_voting(voting_id,user_id) values(?1,?2)",nativeQuery=true)
	void InsertNew(Long voteid,Long user_id);
	

}
