package com.example.demo25.calendar.model;

import java.time.LocalDateTime;
import java.util.*;

public class Meeting {
    private final String meetingId;
    private final String title;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String organizerId;
    private final Map<String, RSVPStatus> attendees;

    public Meeting(String meetingId, String title, LocalDateTime startTime,
                   LocalDateTime endTime, String organizerId, List<String> attendeeIds) {
        this.meetingId = meetingId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizerId = organizerId;
        this.attendees = new HashMap<>();
        for (String id : attendeeIds) {
            attendees.put(id, RSVPStatus.PENDING);
        }
        attendees.put(organizerId, RSVPStatus.ACCEPTED); // organizer auto-RSVP
    }

    public String getMeetingId() { return meetingId; }
    public String getTitle() { return title; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public String getOrganizerId() { return organizerId; }
    public Map<String, RSVPStatus> getAttendees() { return attendees; }
}
