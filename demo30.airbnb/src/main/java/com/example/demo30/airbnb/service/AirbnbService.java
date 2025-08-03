package com.example.demo30.airbnb.service;

import com.example.demo30.airbnb.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class AirbnbService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Property> properties = new ConcurrentHashMap<>();
    private final Map<String, List<Booking>> propertyBookings = new ConcurrentHashMap<>();
    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();

    // User
    public User createUser(String id, String name) {
        User user = new User(id, name);
        users.put(id, user);
        return user;
    }

    // Property
    public Property listProperty(String id, String hostId, String title, String city, double price) {
        Property property = new Property(id, hostId, title, city, price);
        properties.put(id, property);
        propertyBookings.put(id, new ArrayList<>());
        return property;
    }

    public List<Property> search(String city, LocalDate start, LocalDate end) {
        return properties.values().stream()
                .filter(p -> p.getCity().equalsIgnoreCase(city))
                .filter(p -> isAvailable(p.getPropertyId(), start, end))
                .collect(Collectors.toList());
    }

    // Booking
    public Booking book(String bookingId, String guestId, String propertyId,
                        LocalDate startDate, LocalDate endDate) {
        if (!isAvailable(propertyId, startDate, endDate))
            throw new RuntimeException("Property not available");

        Booking booking = new Booking(bookingId, guestId, propertyId, startDate, endDate);
        bookings.put(bookingId, booking);
        propertyBookings.get(propertyId).add(booking);
        return booking;
    }

    private boolean isAvailable(String propertyId, LocalDate start, LocalDate end) {
        List<Booking> booked = propertyBookings.getOrDefault(propertyId, new ArrayList<>());
        for (Booking b : booked) {
            if (start.isBefore(b.getEndDate()) && end.isAfter(b.getStartDate())) {
                return false;
            }
        }
        return true;
    }

    public List<Booking> getBookings(String userId) {
        return bookings.values().stream()
                .filter(b -> b.getGuestId().equals(userId))
                .collect(Collectors.toList());
    }
}
