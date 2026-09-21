package com.example.flashseat.flashseat.controller;

import com.example.flashseat.flashseat.model.Seat;
import com.example.flashseat.flashseat.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/{eventId}/seats")
    public Seat createSeat(
            @PathVariable Long eventId,
            @RequestBody Seat seat) {

        return seatService.createSeat(seat,eventId);
    }

    @GetMapping("/{eventId}/seats")
    public List<Seat> getSeats(@PathVariable Long eventId) {
        return seatService.getSeatsByEvent(eventId);
    }
}