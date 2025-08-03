package com.example.demo30.airbnb.controller;

import com.example.demo30.airbnb.model.*;
import com.example.demo30.airbnb.service.AirbnbService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/airbnb")
public class AirbnbController {

    private final AirbnbService service;

    public AirbnbController(AirbnbService service) {
        this.service = service;
    }

    // User
    @PostMapping("/user")
    public User createUser(@RequestParam String id, @RequestParam String name) {
        return service.createUser(id, name);
    }

    // Host lists property
    @PostMapping("/property")
    public Property listProperty(@RequestParam String id,
                                 @RequestParam String hostId,
                                 @RequestParam String title,
                                 @RequestParam String city,
                                 @RequestParam double price) {
        return service.listProperty(id, hostId, title, city, price);
    }

    // Search available properties
    @GetMapping("/search")
    public List<Property> search(@RequestParam String city,
                                 @RequestParam String start,
                                 @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return service.search(city, startDate, endDate);
    }

    // Book property
    @PostMapping("/book")
    public Booking book(@RequestParam String bookingId,
                        @RequestParam String guestId,
                        @RequestParam String propertyId,
                        @RequestParam String start,
                        @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return service.book(bookingId, guestId, propertyId, startDate, endDate);
    }

    // View bookings
    @GetMapping("/bookings")
    public List<Booking> getBookings(@RequestParam String userId) {
        return service.getBookings(userId);
    }
}
