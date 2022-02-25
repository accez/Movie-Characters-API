package com.example.moviecharactersapi.Repositorys;

import com.example.moviecharactersapi.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie,Integer> {


}
