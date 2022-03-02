package com.example.moviecharactersapi.Controllers;


import com.example.moviecharactersapi.Models.Franchise;
import com.example.moviecharactersapi.Repositorys.FranchiseRepository;
import com.example.moviecharactersapi.Service.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/franchise")
public class FranchiseController {

    private FranchiseRepository repository;
    private FranchiseService service;

    public FranchiseController(FranchiseRepository repository, FranchiseService service) {
        this.repository = repository;
        this.service = service;
    }


    // region CRUD
    // Create
    @Operation(summary = "Create a franchise")
    @PostMapping("/")
    public Franchise addFranchise(@RequestBody Franchise franchise) {
        return repository.save(franchise);
    }

    // Read
    @Operation(summary = "Get all franchises")
    @GetMapping("/")
    public Collection<Franchise> getAllFranchise() {
        return repository.findAll();
    }

    @Operation(summary = "Get a franchise")
    @GetMapping("/{id}")
    public Franchise getFranchiseById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    // Update
    @Operation(summary = "Update a franchise")
    @PutMapping("/{id}")
    public Franchise updateFranchise(@PathVariable int id, @RequestBody Franchise franchise) {
        System.out.println(franchise.toString());
        franchise.setId(id);
        return repository.save(franchise);
    }
    @Operation(summary = "Update a specific part of a franchise e.g the description")
    @PatchMapping("/{id}")
    public Franchise partialUpdateFranchise(@PathVariable int id, @RequestBody Franchise franchise) {
        franchise.setId(id);
        return service.partialUpdate(franchise);
    }

    // Delete
    @Operation(summary = "Delete a franchise")
    @DeleteMapping("/{id}")
    public void deleteFranchise(@PathVariable int id){
        repository.deleteById(id);
    }

    // endregion


}
