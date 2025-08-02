package com.example.demo3.parkinglot.model;

import java.util.List;

public class ParkingFloor {
    private String floorId;
    private List<ParkingSlot> slots;

    public ParkingFloor(String floorId, List<ParkingSlot> slots) {
        this.floorId = floorId;
        this.slots = slots;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public String getFloorId() {
        return floorId;
    }
}
