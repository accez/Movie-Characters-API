package com.example.moviecharactersapi.Service;


import com.example.moviecharactersapi.Models.Movie;
import com.example.moviecharactersapi.Models.MovieCharacter;
import com.example.moviecharactersapi.Repositorys.MovieCharacterRepository;
import com.example.moviecharactersapi.Repositorys.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {


    private final MovieRepository repository;
    private final MovieCharacterRepository characterRepository;

    public MovieService(MovieRepository repository, MovieCharacterRepository characterRepository) {
        this.repository = repository;
        this.characterRepository = characterRepository;
    }

    /**
     * Database method to partially update a movie object, any property being null will not be updated
     * @param movie object with the new property values to store, must include an id
     * @return the new updated movie object
     */
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

    /**
     * Database method to fully update a movie character list, will add all found characters that's in charId list.
     * Any current list WILL BE overruled by the new list.
     * @param movieId of the movie to update
     * @param charIds list of integers of characterIds to add to movie
     * @return new updated movie object
     */
    public Movie fullUpdateCharacterList(int movieId, Collection<Integer> charIds){
        Movie movie = repository.getById(movieId);
        List<MovieCharacter> characterCollection = new ArrayList<>();
        for (Integer charId: charIds) {
            Optional<MovieCharacter> characterOptional = characterRepository.findById(charId);
            if(characterOptional.isPresent()){
                MovieCharacter movieCharacter = characterOptional.get();
                characterCollection.add(movieCharacter);
                movieCharacter.getMovieList().add(movie);
            }
        }
        movie.setMovieCharacterList(characterCollection);
        return repository.save(movie);
    }

    /**
     * Database method to add characters to a movie, will add all characters in charIds list.
     * Current characters will NOT bew overruled.
     * @param movieId of movie to update
     * @param charIds list of integers of characterdIds to add to movie
     * @return new updated movie object.
     */
    public Movie partialUpdateCharacterList(int movieId, Collection<Integer> charIds) {
        Movie movie = repository.getById(movieId);
        for (Integer charId : charIds) {
            if (movie.getMovieCharacterList().stream().noneMatch(character -> Objects.equals(character.getId(), charId))) {
                Optional<MovieCharacter> characterToAddOpt = characterRepository.findById(charId);
                if (characterToAddOpt.isPresent()) {
                    MovieCharacter characterToAdd = characterToAddOpt.get();
                    movie.getMovieCharacterList().add(characterToAdd);
                    characterToAdd.getMovieList().add(movie);
                }
            }
        }
        return repository.save(movie);
    }


}
