package com.example.moviecharactersapi.Controllers;


import com.example.moviecharactersapi.Models.Movie;
import com.example.moviecharactersapi.Repositorys.MovieRepository;
import com.example.moviecharactersapi.Service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieRepository repository;
    private MovieService service;

    public MovieController(MovieRepository repository, MovieService service){
        this.repository = repository;
        this.service = service;
    }


    // region CRUD
    // CREATE
    @PostMapping("/")
    public Movie addMovie(@RequestBody Movie movie) {
        movie = repository.save(movie);
        return movie;
    }
    // READ
    @GetMapping("/")
    public Collection<Movie> getAllMOMovies() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable int id,@RequestBody Movie movie){
        movie.setId(id);
        return repository.save(movie);
    }

    @PatchMapping("/{id}")
    public Movie partialUpdateMovie(@PathVariable int id, @RequestBody Movie movie){
        movie.setId(id);
        return service.partialUpdateMovie(movie);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        repository.deleteById(id);
    }

    //endregion
}
