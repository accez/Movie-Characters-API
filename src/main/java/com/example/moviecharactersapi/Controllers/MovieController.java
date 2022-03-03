package com.example.moviecharactersapi.Controllers;


import com.example.moviecharactersapi.Models.Movie;
import com.example.moviecharactersapi.Repositorys.MovieRepository;
import com.example.moviecharactersapi.Service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    final private MovieRepository repository;
    final private MovieService service;

    public MovieController(MovieRepository repository, MovieService service){
        this.repository = repository;
        this.service = service;
    }


    // region CRUD
    // CREATE
    @Operation(summary = "Create a movie")
    @PostMapping("/")
    public Movie addMovie(@RequestBody Movie movie) {
        if(movie.getMovieCharacterList() == null)
            movie.setMovieCharacterList(new ArrayList<>());
        return repository.save(movie);
    }

    // READ
    @Operation(summary = "Get all movies")
    @GetMapping("/")
    public Collection<Movie> getAllMOMovies() {
        return repository.findAll();
    }

    @Operation(summary = "Get a movie")
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    // UPDATE
    @Operation(summary = "Update a movie")
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable int id,@RequestBody Movie movie){
        movie.setId(id);
        return repository.save(movie);
    }

    @Operation(summary = "Update a specific part of a movie e.g the title")
    @PatchMapping("/{id}")
    public Movie partialUpdateMovie(@PathVariable int id, @RequestBody Movie movie){
        movie.setId(id);
        return service.partialUpdateMovie(movie);
    }

    // DELETE
    @Operation(summary = "Delete a movie")
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        repository.deleteById(id);
    }

    //endregion


    @PutMapping("/updateCharacterList/{id}")
    public Movie updateFullMovieCharacterList(@PathVariable int id, @RequestBody List<Integer> charIds){
        return service.fullUpdateCharacterList(id,charIds);
    }

    @PatchMapping("/updateCharacterList/{id}")
    public Movie updatePartialMovieCharacterList(@PathVariable int id, @RequestBody List<Integer> charIds){
        return service.partialUpdateCharacterList(id,charIds);
    }

}
