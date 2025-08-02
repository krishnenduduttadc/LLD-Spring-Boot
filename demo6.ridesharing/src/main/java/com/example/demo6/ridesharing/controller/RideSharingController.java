package com.example.demo6.ridesharing.controller;

import com.example.demo6.ridesharing.dto.RideRequestDTO;
import com.example.demo6.ridesharing.model.*;
import com.example.demo6.ridesharing.service.RideService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RideSharingController {

    private final RideService rideService;

    public RideSharingController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/register/rider")
    public String registerRider(@RequestBody Rider rider) {
        rideService.registerRider(rider);
        return "Rider registered: " + rider.getUserId();
    }

    @PostMapping("/register/driver")
    public String registerDriver(@RequestBody Driver driver) {
        rideService.registerDriver(driver);
        return "Driver registered: " + driver.getUserId();
    }

    @PostMapping("/ride/create")
    public Ride createRide(@RequestBody RideRequestDTO request) {
        return rideService.createRide(request.getRiderId(), request.getSource(), request.getDestination());
    }

    @PostMapping("/ride/start/{rideId}")
    public String startRide(@PathVariable String rideId) {
        Ride ride = rideService.startRide(rideId);
        return "Ride started: " + ride.getRideId();
    }

    @PostMapping("/ride/end/{rideId}")
    public String endRide(@PathVariable String rideId) {
        Ride ride = rideService.endRide(rideId);
        return "Ride completed: " + ride.getRideId();
    }

    @GetMapping("/ride/{rideId}")
    public Ride getRide(@PathVariable String rideId) {
        return rideService.getRide(rideId);
    }
}
