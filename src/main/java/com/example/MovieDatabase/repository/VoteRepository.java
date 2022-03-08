package com.example.MovieDatabase.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.MovieDatabase.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{
	
	
	// Fetch records by vote_id
	@Query(value ="select count(id)>0 from Voting where vote_id= ?1",nativeQuery=true)
	boolean existsByVoteId(Long voteId);
	
	// Fetch records by vote-id movie_id
	@Query(value ="select count(id)>0 from Voting where vote_id= ?1 AND movie_id=?2",nativeQuery=true)
	boolean existsByVoteMovieId(long voteId, long movieId);
	
	//SET vote by vote_id
	@Modifying
	@Transactional
	@Query(value ="update Voting set vote=?3 where vote_id= ?1 AND movie_id=?2",nativeQuery=true) 
	void addVoteByVoteId(long voteId, long movieId, int vote);
	
	// Insert new Record
	@Modifying
	@Transactional
	@Query(value ="insert into Voting(vote_id,movie_id,vote) values(?1,?2,?3)",nativeQuery=true)
	void InsertNew(Long id,Long movie_id, int vote);
	
	// Fetch upvotes by movie_id
	@Query(value ="select count(vote) from Voting where movie_id= ?1 AND vote=1",nativeQuery=true)
	Long upvotes(Long movie_id);
	
	// Fetch downvotes by movie_id
	@Query(value ="select count(vote) from Voting where movie_id= ?1 AND vote=-1",nativeQuery=true)
	Long downvotes(Long movie_id);

}
