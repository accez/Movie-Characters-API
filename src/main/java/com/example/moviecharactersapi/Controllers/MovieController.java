package com.example.moviecharactersapi.Controllers;


import com.example.moviecharactersapi.Models.Movie;
import com.example.moviecharactersapi.Repositorys.MovieRepository;
import com.example.moviecharactersapi.Service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public Collection<Movie> getAllMOvies() {
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
    public Movie partialUpdateMove(@PathVariable int id, @RequestBody Movie movie){
        movie.setId(id);
        return service.partialUpdateMovie(movie);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteMove(@PathVariable int id) {
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



    @GetMapping("/test")
    public Collection<Integer> test(){
        return new ArrayList<>(List.of(1, 2, 3, 4, 5));
    }


}
