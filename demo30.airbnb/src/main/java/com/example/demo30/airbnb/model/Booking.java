package com.example.demo30.airbnb.model;

import java.time.LocalDate;

public class Booking {
    private final String bookingId;
    private final String guestId;
    private final String propertyId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Booking(String bookingId, String guestId, String propertyId, LocalDate startDate, LocalDate endDate) {
        this.bookingId = bookingId;
        this.guestId = guestId;
        this.propertyId = propertyId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBookingId() { return bookingId; }
    public String getGuestId() { return guestId; }
    public String getPropertyId() { return propertyId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
}
