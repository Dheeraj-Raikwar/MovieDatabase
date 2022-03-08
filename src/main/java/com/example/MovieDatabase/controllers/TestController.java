package com.example.MovieDatabase.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieDatabase.security.services.UserDetailsImpl;
import com.example.MovieDatabase.service.MovieService;
import com.example.MovieDatabase.service.ReviewService;
import com.example.MovieDatabase.service.VoteService;
import com.example.MovieDatabase.Request.AddMovie;
import com.example.MovieDatabase.models.Movie;
import com.example.MovieDatabase.repository.Favourite_genreRepository;
import com.example.MovieDatabase.repository.MovieRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	private MovieRepository movierepository;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private Favourite_genreRepository favourite_genreRepository;
	
	@Autowired
	private VoteService voteService;
	
	@Autowired
	private ReviewService reviewService;
	
	
	
	//Get Movies Api(Public View)
	@GetMapping("/all/movies")
	public ResponseEntity<?> allAccess() {
			List<Movie> movie = movierepository.findAll();
			return new ResponseEntity<>(movie, HttpStatus.OK);
	}
	
	//Get Movies Api(Public View) By Order
		@GetMapping("/all/movies/orderBy")
		public ResponseEntity<?> allAccess(@RequestParam String orderBy) {
			
			if(orderBy.equals("upvote")) {
				List<Movie> movie = movierepository.findByUpvote();
				return new ResponseEntity<>(movie, HttpStatus.OK);
			}
			else if(orderBy.equals("downvote")) {
				List<Movie> movie = movierepository.findByDownvote();
			return new ResponseEntity<>(movie, HttpStatus.OK);
			}
			
			else return new ResponseEntity<>("Invalid Response", HttpStatus.OK);
		
		}
		
		
	
    
	/*       USER Authorized only      */
	
	//Add Movies Api
	@PostMapping("/user/addmovie")
	public ResponseEntity<?> addMovie(@RequestBody AddMovie movie) throws Exception {		
		Movie newMovie = new Movie(movie.getTitle(),movie.getGenre(),movie.getDetails(),movie.getRelease_date(),0.0f);		
		movierepository.save(newMovie);
		return new ResponseEntity<>("Movie Added Successfully", HttpStatus.OK);
	}
	
	//Set favorite genre Api
	@PostMapping("/user/addGenre")
	public ResponseEntity<?> favourite_genre(@AuthenticationPrincipal UserDetailsImpl user,@RequestParam String genre){		
		
		long userId = user.getId();
		if(favourite_genreRepository.existsById(userId)) {
			favourite_genreRepository.setGenre(userId,genre);
			return new ResponseEntity<>("favourite genre updated ", HttpStatus.OK);
		}
		else {
			favourite_genreRepository.InsertNew(userId,genre);
			return new ResponseEntity<>("favourite genre is set", HttpStatus.OK);
		}
		
	}
	
	//Get Recommendations Api (according to favorite genre & order by score)
	@GetMapping("/user/recomm")
       public ResponseEntity<?> favourite_genre(@AuthenticationPrincipal UserDetailsImpl user){		
		long userId = user.getId();
		List<Movie> movies = new ArrayList<Movie>();
	       try {
			movies = movieService.findRecom(userId);
	        return new ResponseEntity<>(movies, HttpStatus.OK);
	       }
	       catch(Exception e){
	    	   return new ResponseEntity<>("No Recommendations Found.", HttpStatus.OK);
	       }
	    }
       
	
	//Get Movie Detail Api
	@GetMapping("/user/getmovie")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getMoviesforUser(@RequestParam long id) {
		Optional<Movie> movie = movierepository.findById(id);
		return new ResponseEntity<>(movie, HttpStatus.OK);
    }
	
	//UpVote/DownVote Api
	@PostMapping("/user/addvote")
	public ResponseEntity<?> addvote(@AuthenticationPrincipal UserDetailsImpl user,@RequestParam long movieId, String vote){
		long userId = user.getId();
		
		//For Upvote
		if(vote.equals("upvote")) {
		voteService.addVoteByUserId(userId,movieId,1);
		return new ResponseEntity<>("Vote added Successfully", HttpStatus.OK);
		}
		//For downvote
		else if (vote.equals("downvote")){
			voteService.addVoteByUserId(userId,movieId,-1);
			return new ResponseEntity<>("Vote added Successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("Invalid Response: Give MovieId and upvote or downvote as params", HttpStatus.OK);
			
		
	}
	
	// Add Review Api
	@PostMapping("/user/addreview")
	public ResponseEntity<?> addreview(@AuthenticationPrincipal UserDetailsImpl user,@RequestParam long movieId, String review) {		
		
		long userId = user.getId();
		
		reviewService.addReviewByUserId(userId,movieId,review);
		
		return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
	}
	

}
