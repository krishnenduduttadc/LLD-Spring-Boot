package com.example.demo4.bookmyshow.model;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    private String bookingId;
    private String userId;
    private Show show;
    private List<Seat> bookedSeats;
    private LocalDateTime bookingTime;

    public Booking(String bookingId, String userId, Show show, List<Seat> bookedSeats) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.bookingTime = LocalDateTime.now();
    }

    public String getBookingId() { return bookingId; }
    public String getUserId() { return userId; }
    public Show getShow() { return show; }
    public List<Seat> getBookedSeats() { return bookedSeats; }
    public LocalDateTime getBookingTime() { return bookingTime; }
}
