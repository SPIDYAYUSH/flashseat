package com.example.flashseat.flashseat.service;

import com.example.flashseat.flashseat.model.Event;
import com.example.flashseat.flashseat.model.Seat;
import com.example.flashseat.flashseat.repo.EventRepository;
import com.example.flashseat.flashseat.repo.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final EventRepository eventRepository;

    public SeatService(
            SeatRepository seatRepository,
            EventRepository eventRepository) {

        this.seatRepository = seatRepository;
        this.eventRepository = eventRepository;
    }

    public Seat createSeat(Seat seat, Long eventId) {

        Event event = eventRepository.findById(eventId).orElseThrow();

        seat.setEvent(event);

        return seatRepository.save(seat);
    }

    public List<Seat> getSeatsByEvent(Long eventId) {
        return seatRepository.findByEventId(eventId);
    }
}