package com.example.MovieDatabase.service;

import java.util.List;
import java.util.Optional;

import com.example.MovieDatabase.models.Movie;


public interface MovieService {
	
	Optional<Movie> findById(Long id);
	
	List<Movie> findByGenre(String genre);
	
	Float findScore(long movie_id);
	
	List<Movie> findByUpvote();
	
	List<Movie> findByDownvote();

    List<Movie> findAll();
    
    List<Movie> findRecom(long id);
    
    void UpdateScore(long id, Float score);
    
    void UpdateMovieScore(long movie_id);
 

}
