package com.example.moviecharactersapi.Service;

import com.example.moviecharactersapi.Models.MovieCharacter;
import com.example.moviecharactersapi.Repositorys.MovieCharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieCharacterService {

    private final MovieCharacterRepository repository;

    public MovieCharacterService(MovieCharacterRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to partialy update a character object, any property being null will not be updated
     * @param character object with the new values to store in database, must include an id
     * @return the new updated character object
     */
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
