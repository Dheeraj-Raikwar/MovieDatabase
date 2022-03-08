package com.example.MovieDatabase.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.MovieDatabase.models.Movie;
import com.example.MovieDatabase.repository.MovieRepository;
import com.example.MovieDatabase.repository.VoteRepository;
import com.example.MovieDatabase.service.MovieService;
import com.example.MovieDatabase.repository.Favourite_genreRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	private final MovieRepository movieRepository;
	private final Favourite_genreRepository favourite_genreRepository;
	private final VoteRepository voteRepository;
	
	public MovieServiceImpl(MovieRepository movieRepository,Favourite_genreRepository favourite_genreRepository,VoteRepository voteRepository) {
		super();
		this.movieRepository = movieRepository;
		this.favourite_genreRepository = favourite_genreRepository;
		this.voteRepository = voteRepository;
	}

	@Override
	public Optional<Movie> findById(Long id) {
		
		return movieRepository.findById(id);
	}

	@Override
	public List<Movie> findAll() {
		
		return movieRepository.findAll();
	}

	@Override
	public List<Movie> findByGenre(String genre) {
		
		return movieRepository.findByGenre(genre);
	}

	@Override
	public Float findScore(long movie_id) {
		
		return movieRepository.findScore(movie_id);
	}

	@Override
	public List<Movie> findByUpvote() {
		
		return movieRepository.findByUpvote();
	}

	@Override
	public List<Movie> findByDownvote() {
		
		return movieRepository.findByDownvote();
	}

	@Override
	public List<Movie> findRecom(long id) {
		
		List<Movie> Fav_list = new ArrayList<Movie>();
		
		Optional<String> genreOp = favourite_genreRepository.findGenre(id);
		
		String genre = genreOp.get();
		
		if(genre!=null)
		Fav_list = movieRepository.findByGenreScore(genre);	    
		
		return Fav_list;
	}

	@Override
	public void UpdateScore(long id, Float score) {
		
		movieRepository.UpdateScore(id, score);
		
	}

	@Override
	public void UpdateMovieScore(long movie_id) {
		long upvote = voteRepository.upvotes(movie_id);
		long downvote = voteRepository.downvotes(movie_id);
		long votes = upvote+downvote;
		
		float score = (float)(upvote)/votes*10;
		
		movieRepository.UpdateScore(movie_id, score);
	}
	
}
