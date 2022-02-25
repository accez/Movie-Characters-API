package com.example.moviecharactersapi.Models;

import javax.persistence.*;

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

    public MovieCharacter() {
    }

    public MovieCharacter(Integer id, String fullName, String alias, String gender, String picture) {
        this.id = id;
        this.fullName = fullName;
        this.alias = alias;
        this.gender = gender;
        this.picture = picture;
    }
    //region GettersAndSetters
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    //endregion
    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", alias='" + alias + '\'' +
                ", gender='" + gender + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
