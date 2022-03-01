package com.example.moviecharactersapi.Service;


import com.example.moviecharactersapi.Models.Movie;
import com.example.moviecharactersapi.Models.MovieCharacter;
import com.example.moviecharactersapi.Repositorys.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {


    private MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public Movie partialUpdateMovie(Movie movie) {
        Movie movieFromDB = repository.getById(movie.getId());


        if (movie.getTitle() != null)
            movieFromDB.setTitle(movie.getTitle());
        if (movie.getDirector() != null)
            movieFromDB.setDirector(movie.getDirector());
        if (movie.getGenre() != null)
            movieFromDB.setGenre(movie.getGenre());
        if (movie.getReleaseYear() != 0)
            movieFromDB.setReleaseYear(movie.getReleaseYear());
        if (movie.getPictureUrl() != null)
            movieFromDB.setPictureUrl(movie.getPictureUrl());
        if (movie.getTrailerUrl() != null)
            movieFromDB.setTrailerUrl(movie.getTrailerUrl());

        if (movie.getMovieCharacterList() != null) {
            movieFromDB.setMovieCharacterList(movie.getMovieCharacterList());
        }

        if (movie.getFranchise() != null) {
            movieFromDB.setFranchise(movie.getFranchise());
        }

        return repository.save(movieFromDB);
    }


}
