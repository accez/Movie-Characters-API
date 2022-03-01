package com.example.moviecharactersapi.Service;

import com.example.moviecharactersapi.Models.Movie;
import com.example.moviecharactersapi.Models.MovieCharacter;
import com.example.moviecharactersapi.Repositorys.MovieCharacterRepoistory;
import org.springframework.stereotype.Service;

@Service
public class MovieCharacterService {

    private MovieCharacterRepoistory repository;

    public MovieCharacterService(MovieCharacterRepoistory repository) {
        this.repository = repository;
    }

    public MovieCharacter partialUpdateMovieCharacter(MovieCharacter character){

        MovieCharacter characterFromDB = repository.getById(character.getId());

        if(character.getFullName() != null)
            characterFromDB.setFullName(character.getFullName());
        if(character.getAlias() != null)
            characterFromDB.setAlias(character.getAlias());
        if(character.getGender() != null)
            characterFromDB.setGender(character.getGender());
        if(character.getPicture() != null)
            characterFromDB.setPicture(character.getPicture());
        return repository.save(characterFromDB);
    }

}
