package com.example.demo4.bookmyshow.controller;

import com.example.demo4.bookmyshow.dto.*;
import com.example.demo4.bookmyshow.model.*;
import com.example.demo4.bookmyshow.service.BookingService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    private List<Show> dummyShows;

    @PostConstruct
    public void initShows() {
        Movie m = new Movie("M1", "Inception", "EN", "Sci-Fi", 120);

        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            seats.add(new Seat("A" + i, 200));
        }

        Show show = new Show("S1", m, LocalDateTime.now().plusHours(2), seats);
        dummyShows = List.of(show);
    }

    @GetMapping("/shows")
    public List<Map<String, Object>> listShows() {
        List<Map<String, Object>> data = new ArrayList<>();
        for (Show show : dummyShows) {
            Map<String, Object> map = new HashMap<>();
            map.put("showId", show.getShowId());
            map.put("movie", show.getMovie().getTitle());
            map.put("startTime", show.getStartTime());
            map.put("availableSeats", show.getSeats().stream()
                    .filter(s -> s.getStatus() == SeatStatus.AVAILABLE)
                    .map(Seat::getSeatNumber)
                    .toList());
            data.add(map);
        }
        return data;
    }

    @PostMapping("/book")
    public BookingResponse book(@RequestBody BookingRequest request) {
        Show show = dummyShows.stream()
                .filter(s -> s.getShowId().equals(request.getShowId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Show not found"));

        Booking booking = bookingService.bookSeats(
                request.getUserId(),
                show,
                request.getSeatNumbers()
        );

        return new BookingResponse(
                booking.getBookingId(),
                booking.getShow().getShowId(),
                booking.getBookedSeats().stream().map(Seat::getSeatNumber).collect(Collectors.toList()),
                booking.getBookingTime()
        );
    }

    @GetMapping("/{bookingId}")
    public BookingResponse getBooking(@PathVariable String bookingId) {
        Booking booking = bookingService.getBooking(bookingId);
        return new BookingResponse(
                booking.getBookingId(),
                booking.getShow().getShowId(),
                booking.getBookedSeats().stream().map(Seat::getSeatNumber).collect(Collectors.toList()),
                booking.getBookingTime()
        );
    }
}
