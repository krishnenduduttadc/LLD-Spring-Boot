package com.example.demo6.ridesharing.model;

public class Location {
    private double latitude;
    private double longitude;

    public Location() {}
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    public double distanceTo(Location other) {
        double dx = this.latitude - other.latitude;
        double dy = this.longitude - other.longitude;
        return Math.sqrt(dx * dx + dy * dy); // Euclidean for simplicity
    }
}
