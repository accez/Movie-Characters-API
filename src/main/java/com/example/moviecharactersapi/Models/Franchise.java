package com.example.moviecharactersapi.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String description;

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
        return "Franchise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
