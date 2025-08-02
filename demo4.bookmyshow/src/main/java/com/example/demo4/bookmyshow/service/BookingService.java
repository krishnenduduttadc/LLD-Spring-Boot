package com.example.demo4.bookmyshow.service;

import com.example.demo4.bookmyshow.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {

    private final SeatLockService seatLockService;
    private final Map<String, Booking> bookings = new HashMap<>();

    public BookingService(SeatLockService seatLockService) {
        this.seatLockService = seatLockService;
    }

    public Booking bookSeats(String userId, Show show, List<String> seatNumbers) {
        // First release any expired locks
        seatLockService.releaseExpiredLocks(show.getShowId(), show.getSeats());

        // Extract the Seat objects
        List<Seat> selectedSeats = new ArrayList<>();
        for (String seatNumber : seatNumbers) {
            show.getSeats().stream()
                .filter(seat -> seat.getSeatNumber().equals(seatNumber))
                .findFirst()
                .ifPresent(selectedSeats::add);
        }

        // Try locking
        boolean locked = seatLockService.lockSeats(show.getShowId(), selectedSeats, userId);
        if (!locked) {
            throw new RuntimeException("Some seats are already locked or booked.");
        }

        // Simulate payment success, confirm booking
        for (Seat seat : selectedSeats) {
            seat.setStatus(SeatStatus.BOOKED);
        }

        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, userId, show, selectedSeats);
        bookings.put(bookingId, booking);
        return booking;
    }

    public Booking getBooking(String bookingId) {
        return bookings.get(bookingId);
    }
}
