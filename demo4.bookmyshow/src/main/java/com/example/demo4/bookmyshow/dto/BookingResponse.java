package com.example.demo4.bookmyshow.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookingResponse {
    private String bookingId;
    private String showId;
    private List<String> seats;
    private LocalDateTime bookingTime;

    public BookingResponse(String bookingId, String showId, List<String> seats, LocalDateTime bookingTime) {
        this.bookingId = bookingId;
        this.showId = showId;
        this.seats = seats;
        this.bookingTime = bookingTime;
    }

    public String getBookingId() { return bookingId; }
    public String getShowId() { return showId; }
    public List<String> getSeats() { return seats; }
    public LocalDateTime getBookingTime() { return bookingTime; }
}
