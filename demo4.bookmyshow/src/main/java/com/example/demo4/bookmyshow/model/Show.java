package com.example.demo4.bookmyshow.model;

import java.time.LocalDateTime;
import java.util.List;

public class Show {
    private String showId;
    private Movie movie;
    private LocalDateTime startTime;
    private List<Seat> seats;

    public Show(String showId, Movie movie, LocalDateTime startTime, List<Seat> seats) {
        this.showId = showId;
        this.movie = movie;
        this.startTime = startTime;
        this.seats = seats;
    }

    public String getShowId() { return showId; }
    public Movie getMovie() { return movie; }
    public LocalDateTime getStartTime() { return startTime; }
    public List<Seat> getSeats() { return seats; }
}
