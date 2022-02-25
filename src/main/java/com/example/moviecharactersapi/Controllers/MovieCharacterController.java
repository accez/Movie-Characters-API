package com.example.moviecharactersapi.Controllers;

import com.example.moviecharactersapi.Models.MovieCharacter;
import com.example.moviecharactersapi.Repositorys.MovieCharacterRepoistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieCharacterController {
    @Autowired
    private MovieCharacterRepoistory movieCharacterRepository;

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
}
