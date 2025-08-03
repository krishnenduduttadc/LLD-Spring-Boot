package com.example.demo25.calendar.controller;

import com.example.demo25.calendar.model.*;
import com.example.demo25.calendar.service.CalendarService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    private final CalendarService service;

    public CalendarController(CalendarService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User register(@RequestParam String id, @RequestParam String name) {
        return service.registerUser(id, name);
    }

    @PostMapping("/meeting")
    public Meeting createMeeting(@RequestParam String meetingId,
                                 @RequestParam String title,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
                                 @RequestParam String organizerId,
                                 @RequestBody List<String> attendees) {
        return service.scheduleMeeting(meetingId, title, start, end, organizerId, attendees);
    }

    @PostMapping("/rsvp")
    public void respond(@RequestParam String meetingId,
                        @RequestParam String userId,
                        @RequestParam RSVPStatus status) {
        service.respondToInvite(meetingId, userId, status);
    }

    @GetMapping("/calendar")
    public List<Meeting> getUserMeetings(@RequestParam String userId) {
        return service.getUserCalendar(userId);
    }
}
