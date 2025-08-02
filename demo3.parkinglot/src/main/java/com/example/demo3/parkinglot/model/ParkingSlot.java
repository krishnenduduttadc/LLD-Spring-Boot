package com.example.demo3.parkinglot.model;

public class ParkingSlot {
    private String slotId;
    private SlotType type;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSlot(String slotId, SlotType type) {
        this.slotId = slotId;
        this.type = type;
        this.isOccupied = false;
    }

    public boolean isAvailableFor(Vehicle vehicle) {
        if (isOccupied) return false;

        return switch (vehicle.getType()) {
            case CAR -> type == SlotType.COMPACT || type == SlotType.LARGE;
            case BIKE -> type == SlotType.BIKE;
            case TRUCK -> type == SlotType.LARGE;
        };
    }

    // Getters and setters
    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }
    public SlotType getType() { return type; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }
    public void setParkedVehicle(Vehicle vehicle) { this.parkedVehicle = vehicle; }
    public String getSlotId() { return slotId; }
}
