package com.example.demo3.parkinglot.model;

import java.time.LocalDateTime;

public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private String slotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(String ticketId, Vehicle vehicle, String slotId) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slotId = slotId;
        this.entryTime = LocalDateTime.now();
    }

    public void markExit() {
        this.exitTime = LocalDateTime.now();
    }

    // Getters
    public Vehicle getVehicle() { return vehicle; }
    public String getSlotId() { return slotId; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public String getTicketId() { return ticketId; }
}
