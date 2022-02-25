package com.example.moviecharactersapi.Controllers;


import com.example.moviecharactersapi.Models.Franchise;
import com.example.moviecharactersapi.Repositorys.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/franchise")
public class FranchiseController {


    @Autowired
    private FranchiseRepository repository;

    @GetMapping("/")
    public Collection<Franchise> getAllFranchise(){
        return repository.findAll();
    }

    @PostMapping("/")
    public Franchise addFranchise(@RequestBody Franchise franchise){
        return repository.save(franchise);
    }

}
