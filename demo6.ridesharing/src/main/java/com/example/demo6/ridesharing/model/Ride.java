package com.example.demo6.ridesharing.model;

public class Ride {
    private String rideId;
    private Rider rider;
    private Driver driver;
    private Location source;
    private Location destination;
    private RideStatus status;

    public Ride(String rideId, Rider rider, Driver driver,
                Location source, Location destination) {
        this.rideId = rideId;
        this.rider = rider;
        this.driver = driver;
        this.source = source;
        this.destination = destination;
        this.status = RideStatus.REQUESTED;
    }

    public String getRideId() { return rideId; }
    public Rider getRider() { return rider; }
    public Driver getDriver() { return driver; }
    public Location getSource() { return source; }
    public Location getDestination() { return destination; }
    public RideStatus getStatus() { return status; }
    public void setStatus(RideStatus status) { this.status = status; }
}
