package com.example.demo4.bookmyshow.service;

import com.example.demo4.bookmyshow.model.Seat;
import com.example.demo4.bookmyshow.model.SeatStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SeatLockService {

    private final Map<String, Map<String, LocalDateTime>> lockedSeats = new HashMap<>();
    private final long lockTimeoutSeconds = 120; // 2 minutes

    public boolean lockSeats(String showId, List<Seat> seats, String userId) {
        lockedSeats.putIfAbsent(showId, new HashMap<>());
        Map<String, LocalDateTime> seatLocks = lockedSeats.get(showId);

        // Check if any seat is already locked
        for (Seat seat : seats) {
            if (seatLocks.containsKey(seat.getSeatNumber()) &&
                seatLocks.get(seat.getSeatNumber()).isAfter(LocalDateTime.now())) {
                return false; // At least one seat is still locked
            }
        }

        // Lock seats
        for (Seat seat : seats) {
            seat.setStatus(SeatStatus.LOCKED);
            seatLocks.put(seat.getSeatNumber(), LocalDateTime.now().plusSeconds(lockTimeoutSeconds));
        }

        return true;
    }

    public void releaseExpiredLocks(String showId, List<Seat> showSeats) {
        if (!lockedSeats.containsKey(showId)) return;

        Map<String, LocalDateTime> seatLocks = lockedSeats.get(showId);
        LocalDateTime now = LocalDateTime.now();

        for (Seat seat : showSeats) {
            String seatNum = seat.getSeatNumber();
            if (seatLocks.containsKey(seatNum) && seatLocks.get(seatNum).isBefore(now)) {
                seat.setStatus(SeatStatus.AVAILABLE);
                seatLocks.remove(seatNum);
            }
        }
    }
}
