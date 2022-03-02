package com.example.moviecharactersapi.Models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;

    @JsonGetter("movies")
    public List<String> moviesAsURIInFranchise() {
        return movies.stream().map(movie -> String.format("/movie/%d", movie.getId())).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "franchise")
    private List<Movie> movies;

    public Franchise() {
    }

    public Franchise(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //region gettersSetters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    //endregion

    @Override
    public String toString() {
        return "Franchise{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }
}
