package com.example.demo4.bookmyshow.model;

import java.util.List;

public class Movie {
    private String movieId;
    private String title;
    private String language;
    private String genre;
    private int durationMinutes;

    public Movie(String movieId, String title, String language, String genre, int durationMinutes) {
        this.movieId = movieId;
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
    }

    // Getters
    public String getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public String getLanguage() { return language; }
    public String getGenre() { return genre; }
    public int getDurationMinutes() { return durationMinutes; }
}
