package com.example.moviecharactersapi.Service;


import com.example.moviecharactersapi.Models.Franchise;
import com.example.moviecharactersapi.Repositorys.FranchiseRepository;
import org.springframework.stereotype.Service;

@Service
public class FranchiseService {

    private FranchiseRepository repository;

    public FranchiseService(FranchiseRepository repository){
        this.repository = repository;
    }


    public Franchise partialUpdate(Franchise franchise){

        Franchise franchiseFromDB = repository.getById(franchise.getId());

        if(franchise.getName() != null)
            franchiseFromDB.setName(franchise.getName());
        if(franchise.getDescription() != null)
            franchiseFromDB.setDescription(franchise.getDescription());
        if(franchise.getMovies() != null)
            franchiseFromDB.setMovies(franchise.getMovies());

        return repository.save(franchiseFromDB);
    }
}
