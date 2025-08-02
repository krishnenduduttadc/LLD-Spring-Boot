package com.example.demo6.ridesharing.model;

public class Driver extends User {
    private Location currentLocation;
    private DriverStatus status;

    public Driver(String userId, String name, Location location) {
        super(userId, name);
        this.currentLocation = location;
        this.status = DriverStatus.IDLE;
    }

    public Location getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(Location location) { this.currentLocation = location; }

    public DriverStatus getStatus() { return status; }
    public void setStatus(DriverStatus status) { this.status = status; }
}
