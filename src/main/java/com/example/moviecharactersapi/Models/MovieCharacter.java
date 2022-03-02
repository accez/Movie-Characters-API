package com.example.moviecharactersapi.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class MovieCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fullName;
    private String alias;
    private String gender;
    private String picture;

    @JsonGetter("movieList")
    public List<String> getMoviesAsURI() {
        return movieList.stream().map(movie -> String.format("/movie/%d", movie.getId())).collect(Collectors.toList());
    }

    @ManyToMany(mappedBy = "movieCharacterList", fetch = FetchType.LAZY)
    private List<Movie> movieList;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "fullName = " + fullName + ", " +
                "alias = " + alias + ", " +
                "gender = " + gender + ", " +
                "picture = " + picture + ")";
    }
}
