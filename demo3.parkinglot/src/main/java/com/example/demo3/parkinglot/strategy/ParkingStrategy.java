package com.example.demo3.parkinglot.strategy;

import com.example.demo3.parkinglot.model.ParkingLot;
import com.example.demo3.parkinglot.model.ParkingSlot;
import com.example.demo3.parkinglot.model.Vehicle;

public interface ParkingStrategy {
    ParkingSlot findSlot(ParkingLot lot, Vehicle vehicle);
}
