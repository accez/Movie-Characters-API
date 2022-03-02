package com.example.moviecharactersapi.Controllers;

import com.example.moviecharactersapi.Models.MovieCharacter;
import com.example.moviecharactersapi.Service.MovieCharacterService;
import com.example.moviecharactersapi.Repositorys.MovieCharacterRepository;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create a actor or actress")
    @PostMapping("/")
    public MovieCharacter createCharacter(@RequestBody MovieCharacter character) {
        return repository.save(character);
    }

    // Read
    @Operation(summary = "Get all actor or actresses")
    @GetMapping("/")
    public List<MovieCharacter> getAllCharacters() {
        return repository.findAll();

    }
    @Operation(summary = "Get a actor or actress")
    @GetMapping("/{id}")
    public MovieCharacter getCharacterById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    // Update
    @Operation(summary = "Update a actor or actress")
    @PutMapping("/{id}")
    public MovieCharacter updateCharacter(@PathVariable int id, @RequestBody MovieCharacter character) {
        character.setId(id);
        return repository.save(character);
    }
    @Operation(summary = "Update a specific part of a actor or actress e.g the name")
    @PatchMapping("/{id}")
    public MovieCharacter partialUpdateCharacter(@PathVariable int id, @RequestBody MovieCharacter character) {
        character.setId(id);
        return service.partialUpdateMovieCharacter(character);
    }

    // Delete
    @Operation(summary = "Delete a actor or actress")
    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable int id){
        repository.deleteById(id);
    }
    // endregion
}

