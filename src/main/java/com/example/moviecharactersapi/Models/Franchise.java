package com.example.moviecharactersapi.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 100)
    private String name;
    private String description;

    @JsonGetter("movies")
    public List<String> moviesAsURIInFranchise() {
        return movies.stream().map(movie -> String.format("/movie/%d", movie.getId())).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "franchise")
    private List<Movie> movies;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ")";
    }
}
