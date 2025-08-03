package com.example.demo26.meetingsystem.controller;

import com.example.demo26.meetingsystem.model.*;
import com.example.demo26.meetingsystem.service.MeetingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/meet")
public class MeetingController {

    private final MeetingService service;

    public MeetingController(MeetingService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User createUser(@RequestParam String id, @RequestParam String name) {
        return service.createUser(id, name);
    }

    @PostMapping("/schedule")
    public Meeting scheduleMeeting(@RequestParam String meetingId,
                                   @RequestParam String title,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                   @RequestParam String hostId) {
        return service.scheduleMeeting(meetingId, title, start, hostId);
    }

    @PostMapping("/join")
    public void join(@RequestParam String meetingId, @RequestParam String userId) {
        service.joinMeeting(meetingId, userId);
    }

    @PostMapping("/end")
    public void end(@RequestParam String meetingId, @RequestParam String hostId) {
        service.endMeeting(meetingId, hostId);
    }

    @PostMapping("/assign-cohost")
    public void assignCoHost(@RequestParam String meetingId,
                             @RequestParam String hostId,
                             @RequestParam String targetUserId) {
        service.assignCoHost(meetingId, hostId, targetUserId);
    }

    @PostMapping("/remove")
    public void removeUser(@RequestParam String meetingId,
                           @RequestParam String hostId,
                           @RequestParam String targetUserId) {
        service.removeUser(meetingId, hostId, targetUserId);
    }

    @GetMapping("/meetings")
    public List<Meeting> getUserMeetings(@RequestParam String userId) {
        return service.getUserMeetings(userId);
    }
}
