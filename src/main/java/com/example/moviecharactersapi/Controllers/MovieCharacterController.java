package com.example.moviecharactersapi.Controllers;

import com.example.moviecharactersapi.Models.MovieCharacter;
import com.example.moviecharactersapi.Service.MovieCharacterService;
import com.example.moviecharactersapi.Repositorys.MovieCharacterRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/character")
public class MovieCharacterController {


    private MovieCharacterRepository repository;
    private MovieCharacterService service;

    public MovieCharacterController(MovieCharacterRepository repository, MovieCharacterService service) {
        this.repository = repository;
        this.service = service;
    }

    // region CRUD

    //Create
    @PostMapping("/")
    public MovieCharacter createCharacter(@RequestBody MovieCharacter character) {
        return repository.save(character);
    }

    // Read
    @GetMapping("/")
    public List<MovieCharacter> getAllCharacters() {
        return repository.findAll();

    }

    @GetMapping("/{id}")
    public MovieCharacter getCharacterById(@PathVariable int id) {
      /*
      MovieCharacter movieCharacter = null;
        if (movieCharacterRepository.existsById(id)){
            movieCharacter = movieCharacterRepository.findById(id).orElse(null);
        }
      */
        return repository.findById(id).orElse(null);
    }

    // Update
    @PutMapping("/{id}")
    public MovieCharacter updateCharacter(@PathVariable int id, @RequestBody MovieCharacter character) {
        character.setId(id);
        return repository.save(character);
    }

    @PatchMapping("/{id}")
    public MovieCharacter partialUpdateCharacter(@PathVariable int id, @RequestBody MovieCharacter character) {
        character.setId(id);
        return service.partialUpdateMovieCharacter(character);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable int id){
        repository.deleteById(id);
    }
    // endregion
}

