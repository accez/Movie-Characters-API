package com.example.moviecharactersapi.Repositorys;

import com.example.moviecharactersapi.Models.Movie;
import com.example.moviecharactersapi.Models.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Integer> {

    List<MovieCharacter> findAllByMovieListContaining(Movie movie);

}
