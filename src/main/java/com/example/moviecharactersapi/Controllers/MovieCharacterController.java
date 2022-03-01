package com.example.moviecharactersapi.Controllers;

import com.example.moviecharactersapi.Models.MovieCharacter;
import com.example.moviecharactersapi.Repositorys.MovieCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieCharacterController {
    @Autowired
    private MovieCharacterRepository movieCharacterRepository;

    //POST
    @PostMapping("/character/add")
    public MovieCharacter createCharacter(@RequestBody MovieCharacter character){
        character = movieCharacterRepository.save(character);
        return character;
    }

    //GET
    @GetMapping("/character/all")
    public List<MovieCharacter> getAllCharacters(){
        return movieCharacterRepository.findAll();
    }

    @GetMapping("/character/{id}")
    public MovieCharacter getACharacter(@PathVariable int id){

        MovieCharacter movieCharacter = null;
        if (movieCharacterRepository.existsById(id)){
            movieCharacter = movieCharacterRepository.findById(id).orElse(null);
        }
        return movieCharacter;
    }

    //PUT
    @PutMapping("/character/{id}")
    public MovieCharacter replaceAMovieCharacter(@RequestBody MovieCharacter newMovieCharacter, @PathVariable int id){
        return movieCharacterRepository.findById(id).map(movieCharacter -> {
            movieCharacter.setFullName(newMovieCharacter.getFullName());
            movieCharacter.setAlias(newMovieCharacter.getAlias());
            movieCharacter.setGender(newMovieCharacter.getGender());
            movieCharacter.setPicture(newMovieCharacter.getPicture());
            return movieCharacterRepository.save(movieCharacter);
        }).orElseGet(() -> {
            newMovieCharacter.setId(id);
            return movieCharacterRepository.save(newMovieCharacter);
        });
    }

    //Delete
    @DeleteMapping("/character/{id}")
    public void deleteMovieCharacter(@PathVariable int id){
        movieCharacterRepository.deleteById(id);
    }
}

