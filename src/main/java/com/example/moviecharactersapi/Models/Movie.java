package com.example.moviecharactersapi.Models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String pictureUrl;
    private String trailerUrl;


    @JsonGetter("movieCharacterList")
    public List<String> getCharactersAsURI() {
        return movieCharacterList.stream().map(character -> String.format("/character/%d", character.getId())).collect(Collectors.toList());
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_list", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "character_id"))
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

    public Movie() {
    }

    public Movie(Integer id, String title, String genre, int releaseYear, String director, String pictureUrl, String trailerUrl) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.pictureUrl = pictureUrl;
        this.trailerUrl = trailerUrl;
    }

    //region gettersSetters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public List<MovieCharacter> getMovieCharacterList() {
        return movieCharacterList;
    }

    public void setMovieCharacterList(List<MovieCharacter> movieCharacterList) {
        this.movieCharacterList = movieCharacterList;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    //endregion

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title='" + title + '\'' + ", genre='" + genre + '\'' + ", releaseYear=" + releaseYear + ", director='" + director + '\'' + ", pictureUrl='" + pictureUrl + '\'' + ", trailerUrl='" + trailerUrl + '\'' + '}';
    }
}
