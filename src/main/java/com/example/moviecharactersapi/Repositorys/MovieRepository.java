package com.example.moviecharactersapi.Repositorys;

import com.example.moviecharactersapi.Models.Franchise;
import com.example.moviecharactersapi.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    List<Movie> findAllByFranchise(Franchise franchise);
}
