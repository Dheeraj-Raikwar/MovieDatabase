package com.example.MovieDatabase.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.MovieDatabase.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	//Fetch movie by movie_id
	@Query(value ="select * from movie where id= ?1",nativeQuery=true) 
	Optional<Movie> findById(long id);
	
	//Fetch movie by genre
	@Query(value ="select * from movie where genre= ?1",nativeQuery=true) 
	List<Movie> findByGenre(String genre);
	
	//Fetch score by movie_id
	@Query(value ="select score from movie where id= ?1",nativeQuery=true) 	
	Float findScore(long id);
	
	//Fetch movies order by upvote
	@Query(value ="select * from movie order by score desc;",nativeQuery=true) 	
	List<Movie> findByUpvote();
	
	//Fetch movies order by downvote
	@Query(value ="select * from movie order by score;",nativeQuery=true)
	List<Movie> findByDownvote();
	
	//Fetch movies by genre and score(Recommended movies)
	@Query(value ="select * from movie where genre= ?1 order by score desc;",nativeQuery=true)
	List<Movie> findByGenreScore(String genre);
	
	//Update Score by movie_id
	@Modifying
	@Transactional
	@Query(value ="update movie set score=?2 where id= ?1",nativeQuery=true) 
	void UpdateScore(long id, float score);

}
