package com.example.moviecharactersapi.Models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 100)
    private String title;
    @Column(length = 100)
    private String genre;
    private int releaseYear;
    @Column(length = 45)
    private String director;
    private String pictureUrl;
    private String trailerUrl;

    @JsonGetter("movieCharacterList")
    public List<String> getCharactersAsURI() {
        return movieCharacterList.stream()
                .map(character -> String.format("/character/%d", character.getId()))
                .collect(Collectors.toList());
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_list", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "character_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<MovieCharacter> movieCharacterList;


    @JsonGetter("franchise")
    public String getMoviesInFranchiseAsURI() {
        if (franchise != null) {
            return "/franchise/" + franchise.getId();
        } else {
            return null;
        }
    }

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                ", director='" + director + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                ", movieCharacterList=" + movieCharacterList +
                ", franchise=" + franchise +
                '}';
    }
}
