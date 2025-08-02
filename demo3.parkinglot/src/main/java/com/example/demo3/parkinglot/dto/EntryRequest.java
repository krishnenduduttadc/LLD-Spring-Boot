package com.example.demo3.parkinglot.dto;

public class EntryRequest {
    private String registrationNumber;
    private String vehicleType; // "CAR", "BIKE", "TRUCK"

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
}
