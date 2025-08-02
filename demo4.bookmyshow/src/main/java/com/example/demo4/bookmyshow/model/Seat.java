package com.example.demo4.bookmyshow.model;

public class Seat {
    private String seatNumber;       // e.g., A1, B5
    private SeatStatus status;
    private double price;

    public Seat(String seatNumber, double price) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatNumber() { return seatNumber; }
    public SeatStatus getStatus() { return status; }
    public void setStatus(SeatStatus status) { this.status = status; }
    public double getPrice() { return price; }
}
