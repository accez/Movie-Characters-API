package com.example.moviecharactersapi.Repositorys;

import com.example.moviecharactersapi.Models.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCharacterRepoistory extends JpaRepository<MovieCharacter, Integer> {

}
