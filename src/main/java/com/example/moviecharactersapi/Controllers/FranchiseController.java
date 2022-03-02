package com.example.moviecharactersapi.Controllers;


import com.example.moviecharactersapi.Models.Franchise;
import com.example.moviecharactersapi.Repositorys.FranchiseRepository;
import com.example.moviecharactersapi.Service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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
    @PostMapping("/")
    public Franchise addFranchise(@RequestBody Franchise franchise) {
        return repository.save(franchise);
    }

    // Read
    @GetMapping("/")
    public Collection<Franchise> getAllFranchise() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Franchise getFranchiseById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    // Update

    @PutMapping("/{id}")
    public Franchise updateFranchise(@PathVariable int id, @RequestBody Franchise franchise) {
        System.out.println(franchise.toString());
        franchise.setId(id);
        return repository.save(franchise);
    }

    @PatchMapping("/{id}")
    public Franchise partialUpdateFranchise(@PathVariable int id, @RequestBody Franchise franchise) {
        franchise.setId(id);
        return service.partialUpdate(franchise);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteFranchise(@PathVariable int id){
        repository.deleteById(id);
    }

    // endregion


    @PutMapping("/updateFranchiseList/{id}")
    public Franchise updateFullMovieInFranchise(@PathVariable int id, @RequestBody List<Integer> movieIds){
        return service.fullUpdateMovieFranchiseList(id,movieIds);
    }

    @PatchMapping("/updateFranchiseList/{id}")
    public Franchise updatePartialMovieInFranchise(@PathVariable int id, @RequestBody List<Integer> movieIds){
        return service.partialUpdateMovieFranchiseList(id,movieIds);
    }


}
