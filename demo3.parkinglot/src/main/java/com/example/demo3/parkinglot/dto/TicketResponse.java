package com.example.demo3.parkinglot.dto;

import java.time.LocalDateTime;

public class TicketResponse {
    private String ticketId;
    private String slotId;
    private String vehicleType;
    private LocalDateTime entryTime;

    public TicketResponse(String ticketId, String slotId, String vehicleType, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.slotId = slotId;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
    }

    public String getTicketId() { return ticketId; }
    public String getSlotId() { return slotId; }
    public String getVehicleType() { return vehicleType; }
    public LocalDateTime getEntryTime() { return entryTime; }
}
