package com.example.demo6.ridesharing.dto;

import com.example.demo6.ridesharing.model.Location;

public class RideRequestDTO {
    private String riderId;
    private Location source;
    private Location destination;

    public String getRiderId() { return riderId; }
    public void setRiderId(String riderId) { this.riderId = riderId; }

    public Location getSource() { return source; }
    public void setSource(Location source) { this.source = source; }

    public Location getDestination() { return destination; }
    public void setDestination(Location destination) { this.destination = destination; }
}
