package com.example.demo26.meetingsystem.service;

import com.example.demo26.meetingsystem.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MeetingService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Meeting> meetings = new ConcurrentHashMap<>();

    public User createUser(String id, String name) {
        User user = new User(id, name);
        users.put(id, user);
        return user;
    }

    public Meeting scheduleMeeting(String meetingId, String title, LocalDateTime startTime, String hostId) {
        if (!users.containsKey(hostId)) throw new RuntimeException("Host user not found");
        Meeting meeting = new Meeting(meetingId, title, startTime, hostId);
        meetings.put(meetingId, meeting);
        return meeting;
    }

    public void joinMeeting(String meetingId, String userId) {
        Meeting meeting = meetings.get(meetingId);
        if (meeting == null) throw new RuntimeException("Meeting not found");

        meeting.getParticipants().putIfAbsent(userId, MeetingRole.PARTICIPANT);
    }

    public void endMeeting(String meetingId, String hostId) {
        Meeting meeting = meetings.get(meetingId);
        if (meeting == null || !isHost(meeting, hostId)) throw new RuntimeException("Only host can end the meeting");
        meeting.setStatus(MeetingStatus.ENDED);
    }

    public List<Meeting> getUserMeetings(String userId) {
        List<Meeting> result = new ArrayList<>();
        for (Meeting m : meetings.values()) {
            if (m.getParticipants().containsKey(userId)) result.add(m);
        }
        return result;
    }

    private boolean isHost(Meeting meeting, String userId) {
        return meeting.getParticipants().getOrDefault(userId, MeetingRole.PARTICIPANT) == MeetingRole.HOST;
    }

    public void assignCoHost(String meetingId, String hostId, String targetUserId) {
        Meeting meeting = meetings.get(meetingId);
        if (meeting == null || !isHost(meeting, hostId)) throw new RuntimeException("Only host can assign co-host");

        meeting.getParticipants().put(targetUserId, MeetingRole.CO_HOST);
    }

    public void removeUser(String meetingId, String hostId, String targetUserId) {
        Meeting meeting = meetings.get(meetingId);
        if (meeting == null || !isHost(meeting, hostId)) throw new RuntimeException("Only host can remove users");

        if (hostId.equals(targetUserId)) throw new RuntimeException("Host cannot remove self");

        meeting.getParticipants().remove(targetUserId);
    }
}
