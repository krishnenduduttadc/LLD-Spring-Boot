package com.example.demo25.calendar.service;

import com.example.demo25.calendar.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Meeting> meetings = new ConcurrentHashMap<>();

    public User registerUser(String id, String name) {
        User user = new User(id, name);
        users.put(id, user);
        return user;
    }

    public Meeting scheduleMeeting(String meetingId, String title, LocalDateTime start,
                                   LocalDateTime end, String organizerId, List<String> attendeeIds) {
        // Conflict check
        for (Meeting m : meetings.values()) {
            if ((m.getAttendees().containsKey(organizerId) || attendeeIds.contains(organizerId)) &&
                overlaps(start, end, m.getStartTime(), m.getEndTime())) {
                throw new RuntimeException("Conflict with another meeting.");
            }
        }

        Meeting meeting = new Meeting(meetingId, title, start, end, organizerId, attendeeIds);
        meetings.put(meetingId, meeting);
        return meeting;
    }

    public void respondToInvite(String meetingId, String userId, RSVPStatus status) {
        Meeting meeting = meetings.get(meetingId);
        if (meeting == null || !meeting.getAttendees().containsKey(userId))
            throw new RuntimeException("Invalid user or meeting");

        meeting.getAttendees().put(userId, status);
    }

    public List<Meeting> getUserCalendar(String userId) {
        return meetings.values().stream()
                .filter(m -> m.getAttendees().containsKey(userId))
                .sorted(Comparator.comparing(Meeting::getStartTime))
                .collect(Collectors.toList());
    }

    private boolean overlaps(LocalDateTime s1, LocalDateTime e1,
                             LocalDateTime s2, LocalDateTime e2) {
        return !(e1.isBefore(s2) || s1.isAfter(e2));
    }
}
