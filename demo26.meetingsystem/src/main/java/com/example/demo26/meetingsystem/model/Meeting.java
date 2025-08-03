package com.example.demo26.meetingsystem.model;

import java.time.LocalDateTime;
import java.util.*;

public class Meeting {
    private final String meetingId;
    private final String title;
    private final LocalDateTime startTime;
    private MeetingStatus status;
    private final Map<String, MeetingRole> participants; // userId → role

    public Meeting(String meetingId, String title, LocalDateTime startTime, String hostId) {
        this.meetingId = meetingId;
        this.title = title;
        this.startTime = startTime;
        this.status = MeetingStatus.SCHEDULED;
        this.participants = new HashMap<>();
        this.participants.put(hostId, MeetingRole.HOST);
    }

    public String getMeetingId() { return meetingId; }
    public String getTitle() { return title; }
    public LocalDateTime getStartTime() { return startTime; }
    public MeetingStatus getStatus() { return status; }
    public void setStatus(MeetingStatus status) { this.status = status; }
    public Map<String, MeetingRole> getParticipants() { return participants; }
}
