package com.example.demo3.parkinglot.model;

public abstract class Vehicle {
    private String registrationNumber;
    private VehicleType type;

    public Vehicle(String registrationNumber, VehicleType type) {
        this.registrationNumber = registrationNumber;
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getType() {
        return type;
    }
}
