package com.example.demo3.parkinglot.model;

import java.util.List;

public class ParkingLot {
    private List<ParkingFloor> floors;

    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }
}
