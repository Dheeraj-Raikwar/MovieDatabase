package com.example.MovieDatabase.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.MovieDatabase.models.Favourite_genre;

public interface Favourite_genreRepository extends JpaRepository<Favourite_genre, Long>{
	
	    //Fetch genre by user_id
		@Query(value ="select genre from favourite_genre where user_id= ?1",nativeQuery=true) 
		Optional<String> findGenre(long id);
		
		//SET genre by user_id
		@Modifying
		@Transactional
		@Query(value ="update favourite_genre set genre=?2 where user_id= ?1",nativeQuery=true) 
		void setGenre(long id, String genre);
		
		
		// Insert new Record
		@Modifying
		@Transactional
		@Query(value ="insert into favourite_genre(user_id,genre) values(?1,?2)",nativeQuery=true)
		void InsertNew(long id, String genre);
}
