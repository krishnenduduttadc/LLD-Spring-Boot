package com.example.demo30.airbnb.model;

public class Property {
    private final String propertyId;
    private final String hostId;
    private final String title;
    private final String city;
    private final double pricePerNight;

    public Property(String propertyId, String hostId, String title, String city, double pricePerNight) {
        this.propertyId = propertyId;
        this.hostId = hostId;
        this.title = title;
        this.city = city;
        this.pricePerNight = pricePerNight;
    }

    public String getPropertyId() { return propertyId; }
    public String getHostId() { return hostId; }
    public String getTitle() { return title; }
    public String getCity() { return city; }
    public double getPricePerNight() { return pricePerNight; }
}
