package com.example.demo3.parkinglot.strategy;

import com.example.demo3.parkinglot.model.*;

public class FirstAvailableStrategy implements ParkingStrategy {

    @Override
    public ParkingSlot findSlot(ParkingLot lot, Vehicle vehicle) {
        for (ParkingFloor floor : lot.getFloors()) {
            for (ParkingSlot slot : floor.getSlots()) {
                if (slot.isAvailableFor(vehicle)) {
                    return slot;
                }
            }
        }
        return null; // No slot found
    }
}
