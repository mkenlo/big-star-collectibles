package com.example.collectibles.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message="Email cannot be empty")
    @Email(message = "Should be a well-formed email address")

    private String email;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 1, max = 20, message="Size must be between 1 and 20")
    private String name;

    private String message;
    @NotEmpty(message=" Gender cannot be empty")
    private String gender;
    private boolean newsLetter;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message = "Date of Birth cannot be a future date")
    @NotNull(message = "Date of Birth cannot be empty")
    private Date dateOfBirth;
    @NotEmpty( message="Cannot be empty")
    private String favoriteCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(boolean newsLetter) {
        this.newsLetter = newsLetter;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFavoriteCollection() {
        return favoriteCollection;
    }

    public void setFavoriteCollection(String favoriteCollection) {
        this.favoriteCollection = favoriteCollection;
    }
}