package com.example.demo4.bookmyshow.dto;

import java.util.List;

public class BookingRequest {
    private String userId;
    private String showId;
    private List<String> seatNumbers;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getShowId() { return showId; }
    public void setShowId(String showId) { this.showId = showId; }

    public List<String> getSeatNumbers() { return seatNumbers; }
    public void setSeatNumbers(List<String> seatNumbers) { this.seatNumbers = seatNumbers; }
}
