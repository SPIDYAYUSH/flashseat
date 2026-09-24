package com.example.flashseat.flashseat.controller;

import com.example.flashseat.flashseat.service.SeatLockService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat-lock")
public class SeatLockController {

    private final SeatLockService seatLockService;

    public SeatLockController(SeatLockService seatLockService) {
        this.seatLockService = seatLockService;
    }

    @PostMapping("/{eventId}/{seatId}/{userId}")
    public String lockSeat(
            @PathVariable Long eventId,
            @PathVariable Long seatId,
            @PathVariable Long userId) {

        boolean locked =
                seatLockService.lockSeat(eventId, seatId, userId);

        if (locked) {
            return "Seat locked successfully";
        }

        return "Seat is already locked";
    }

    @DeleteMapping("/{eventId}/{seatId}/{userId}")
    public String unlockSeat(
            @PathVariable Long eventId,
            @PathVariable Long seatId,
            @PathVariable Long userId) {

        seatLockService.unlockSeat(eventId, seatId, userId);

        return "Seat unlocked";
    }

    @GetMapping("/{eventId}/{seatId}")
    public boolean isLocked(
            @PathVariable Long eventId,
            @PathVariable Long seatId) {

        return seatLockService.isSeatLocked(eventId, seatId);
    }
}