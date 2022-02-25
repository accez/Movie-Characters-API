package com.example.moviecharactersapi.Controllers;


import com.example.moviecharactersapi.Models.Movie;
import com.example.moviecharactersapi.Repositorys.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @GetMapping("/")
    public Collection<Movie> getAllMOvies(){
       return repository.findAll();
    }


    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id){
        return repository.findById(id).orElse(null);
    }


    @PostMapping("/")
    public Movie addMovie(@RequestBody Movie movie){
        movie =  repository.save(movie);
        return movie;
    }


    @DeleteMapping("/{id}")
    public void deleteMove(@PathVariable int id){
        repository.deleteById(id);
    }

}
