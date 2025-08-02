package com.example.demo6.ridesharing.matching;

import com.example.demo6.ridesharing.model.*;

import java.util.List;

public class NearestDriverStrategy implements MatchingStrategy {

    @Override
    public Driver findDriver(Rider rider, Location source, List<Driver> drivers) {
        Driver bestDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : drivers) {
            if (driver.getStatus() != DriverStatus.IDLE) continue;

            double dist = source.distanceTo(driver.getCurrentLocation());
            if (dist < minDistance) {
                minDistance = dist;
                bestDriver = driver;
            }
        }

        return bestDriver;
    }
}
