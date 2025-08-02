package com.example.demo6.ridesharing.service;

import com.example.demo6.ridesharing.matching.MatchingStrategy;
import com.example.demo6.ridesharing.matching.NearestDriverStrategy;
import com.example.demo6.ridesharing.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RideService {

    private final Map<String, Rider> riders = new HashMap<>();
    private final Map<String, Driver> drivers = new HashMap<>();
    private final Map<String, Ride> rides = new HashMap<>();

    private final MatchingStrategy matchingStrategy = new NearestDriverStrategy();

    public void registerRider(Rider rider) {
        riders.put(rider.getUserId(), rider);
    }

    public void registerDriver(Driver driver) {
        drivers.put(driver.getUserId(), driver);
    }

    public Ride createRide(String riderId, Location source, Location destination) {
        Rider rider = riders.get(riderId);
        if (rider == null) throw new RuntimeException("Rider not found");

        List<Driver> allDrivers = new ArrayList<>(drivers.values());
        Driver driver = matchingStrategy.findDriver(rider, source, allDrivers);

        if (driver == null) {
            throw new RuntimeException("No available drivers nearby");
        }

        driver.setStatus(DriverStatus.ON_TRIP);

        String rideId = UUID.randomUUID().toString();
        Ride ride = new Ride(rideId, rider, driver, source, destination);
        rides.put(rideId, ride);

        return ride;
    }

    public Ride startRide(String rideId) {
        Ride ride = getRide(rideId);
        if (ride.getStatus() != RideStatus.REQUESTED) {
            throw new IllegalStateException("Ride already started or completed");
        }

        ride.setStatus(RideStatus.IN_PROGRESS);
        return ride;
    }

    public Ride endRide(String rideId) {
        Ride ride = getRide(rideId);
        if (ride.getStatus() != RideStatus.IN_PROGRESS) {
            throw new IllegalStateException("Ride not in progress");
        }

        ride.setStatus(RideStatus.COMPLETED);
        ride.getDriver().setStatus(DriverStatus.IDLE);
        ride.getDriver().setCurrentLocation(ride.getDestination());

        return ride;
    }

    public Ride getRide(String rideId) {
        Ride ride = rides.get(rideId);
        if (ride == null) throw new RuntimeException("Ride not found");
        return ride;
    }
}
