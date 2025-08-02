package com.example.demo3.parkinglot.controller;

import com.example.demo3.parkinglot.dto.*;
import com.example.demo3.parkinglot.model.*;
import com.example.demo3.parkinglot.service.ParkingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService service;

    @PostMapping("/entry")
    public TicketResponse enterParking(@RequestBody EntryRequest request) {
        Vehicle vehicle = switch (request.getVehicleType().toUpperCase()) {
            case "CAR" -> new Car(request.getRegistrationNumber());
            case "BIKE" -> new Bike(request.getRegistrationNumber());
            default -> throw new IllegalArgumentException("Unsupported vehicle type");
        };

        Ticket ticket = service.parkVehicle(vehicle);
        return new TicketResponse(
                ticket.getTicketId(),
                ticket.getSlotId(),
                vehicle.getType().name(),
                ticket.getEntryTime()
        );
    }

    @PostMapping("/exit")
    public String exitParking(@RequestBody ExitRequest request) {
        int fee = service.unparkVehicle(request.getTicketId());
        return "Parking fee: ₹" + fee;
    }

    @GetMapping("/slots")
    public Map<String, String> viewSlots() {
        Map<String, String> status = new LinkedHashMap<>();
        for (ParkingFloor floor : service.getLot().getFloors()) {
            for (ParkingSlot slot : floor.getSlots()) {
                String key = floor.getFloorId() + "-" + slot.getSlotId();
                String value = slot.isOccupied()
                        ? "Occupied by " + slot.getParkedVehicle().getRegistrationNumber()
                        : "Free";
                status.put(key, value);
            }
        }
        return status;
    }
}
