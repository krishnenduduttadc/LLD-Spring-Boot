package com.example.demo3.parkinglot.service;

import com.example.demo3.parkinglot.model.*;
import com.example.demo3.parkinglot.strategy.FirstAvailableStrategy;
import com.example.demo3.parkinglot.strategy.ParkingStrategy;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.*;

@Service
public class ParkingService {

    private ParkingLot lot;
    private ParkingStrategy strategy = new FirstAvailableStrategy();
    private Map<String, Ticket> activeTickets = new HashMap<>();

    @PostConstruct
    public void init() {
        List<ParkingSlot> floor1Slots = Arrays.asList(
                new ParkingSlot("F1-S1", SlotType.COMPACT),
                new ParkingSlot("F1-S2", SlotType.BIKE),
                new ParkingSlot("F1-S3", SlotType.LARGE)
        );

        ParkingFloor floor1 = new ParkingFloor("F1", floor1Slots);
        lot = new ParkingLot(List.of(floor1));
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSlot slot = strategy.findSlot(lot, vehicle);
        if (slot == null) {
            throw new RuntimeException("No available slot for vehicle type: " + vehicle.getType());
        }

        slot.setOccupied(true);
        slot.setParkedVehicle(vehicle);

        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId, vehicle, slot.getSlotId());
        activeTickets.put(ticketId, ticket);
        return ticket;
    }

    public int unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Invalid ticket ID");
        }

        ticket.markExit();

        for (ParkingFloor floor : lot.getFloors()) {
            for (ParkingSlot slot : floor.getSlots()) {
                if (slot.getSlotId().equals(ticket.getSlotId())) {
                    slot.setOccupied(false);
                    slot.setParkedVehicle(null);
                }
            }
        }

        // Simplified pricing: ₹10/hour (rounded up)
        long hours = Math.max(1, java.time.Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toHours());
        return (int) (hours * 10);
    }

    public ParkingLot getLot() {
        return lot;
    }
}
