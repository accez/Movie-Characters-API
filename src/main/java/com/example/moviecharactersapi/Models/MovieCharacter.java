package com.example.moviecharactersapi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movie_character")
public class MovieCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fullName;
    private String alias;
    private String gender;
    private String picture;

    @ManyToMany(mappedBy = "movieCharacterList",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Movie> movieList;

}
