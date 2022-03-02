package com.example.moviecharactersapi.Service;


import com.example.moviecharactersapi.Models.Franchise;
import com.example.moviecharactersapi.Models.Movie;
import com.example.moviecharactersapi.Models.MovieCharacter;
import com.example.moviecharactersapi.Repositorys.FranchiseRepository;
import com.example.moviecharactersapi.Repositorys.MovieCharacterRepository;
import com.example.moviecharactersapi.Repositorys.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FranchiseService {

    private FranchiseRepository repository;
    private MovieRepository movieRepository;
    private MovieCharacterRepository characterRepository;

    public FranchiseService(FranchiseRepository repository, MovieRepository movieRepository, MovieCharacterRepository characterRepository) {
        this.repository = repository;
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
    }

    public Franchise partialUpdate(Franchise franchise){

        Franchise franchiseFromDB = repository.getById(franchise.getId());

        if(franchise.getName() != null)
            franchiseFromDB.setName(franchise.getName());
        if(franchise.getDescription() != null)
            franchiseFromDB.setDescription(franchise.getDescription());
        if(franchise.getMovies() != null)
            franchiseFromDB.setMovies(franchise.getMovies());

        return repository.save(franchiseFromDB);
    }

    public Franchise fullUpdateMovieFranchiseList(int franchiseId, List<Integer> movieIds){
        Franchise franchise = repository.getById(franchiseId);
        List<Movie> currentMovieList = movieRepository.findAllByFranchise(franchise);
        List<Movie> newMovieList = new ArrayList<>(currentMovieList.stream().filter(movie -> movieIds.contains(movie.getId())).toList());

        currentMovieList.stream().filter(movie -> !movieIds.contains(movie.getId())).forEach(movie -> movie.setFranchise(null));
        movieRepository.saveAll(currentMovieList.stream().filter(movie -> !movieIds.contains(movie.getId())).toList());


        for (Integer movieId: movieIds ) {
            if(newMovieList.stream().noneMatch(movie -> Objects.equals(movie.getId(), movieId))){
                Optional<Movie> movieOptional = movieRepository.findById(movieId);
                if(movieOptional.isPresent()){
                    Movie movie = movieOptional.get();
                    newMovieList.add(movie);
                    movie.setFranchise(franchise);
                    movieRepository.save(movie);
                }
            }
        }
        franchise.setMovies(newMovieList);
        return repository.save(franchise);
    }

    public Franchise partialUpdateMovieFranchiseList(int franchiseId, List<Integer> movieIds){
        Franchise franchise = repository.getById(franchiseId);

        for (Integer movieId: movieIds) {
            if(franchise.getMovies().stream().noneMatch(movie -> Objects.equals(movie.getId(), movieId))){
                Optional<Movie> movieOptional = movieRepository.findById(movieId);
                if(movieOptional.isPresent()){
                    Movie movie = movieOptional.get();
                    franchise.getMovies().add(movie);
                    movie.setFranchise(franchise);
                    movieRepository.save(movie);
                }
            }
        }
        return repository.save(franchise);
    }

    public void removeFranchise(int id){
        Franchise franchise = repository.getById(id);
        List<Movie> movieList = movieRepository.findAllByFranchise(franchise);
        movieList.forEach(movie -> movie.setFranchise(null));
        movieRepository.saveAll(movieList);
        repository.delete(franchise);

    }

    public List<MovieCharacter> getAllMovieCharactersInFranchise(int id){

        Franchise franchise = repository.getById(id);

        List<Movie> movieList = movieRepository.findAllByFranchise(franchise);

        List<MovieCharacter> returnList = new ArrayList<>();

        for (Movie movie: movieList) {
            List<MovieCharacter> movieCharacterList = characterRepository.findAllByMovieListContaining(movie);
            for (MovieCharacter character: movieCharacterList) {
                if(!returnList.contains(character))
                    returnList.add(character);
            }

        }
        return returnList;
    }

}
