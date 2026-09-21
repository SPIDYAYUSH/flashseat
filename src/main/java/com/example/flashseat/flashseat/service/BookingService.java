package com.example.flashseat.flashseat.service;

import com.example.flashseat.flashseat.model.Booking;
import com.example.flashseat.flashseat.model.Seat;
import com.example.flashseat.flashseat.model.SeatStatus;
import com.example.flashseat.flashseat.repo.BookingRepository;
import com.example.flashseat.flashseat.repo.UserRepository;
import com.example.flashseat.flashseat.repo.EventRepository;
import com.example.flashseat.flashseat.repo.SeatRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final SeatRepository seatRepository;

    public BookingService(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            EventRepository eventRepository,
            SeatRepository seatRepository) {

        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.seatRepository = seatRepository;
    }

    public Booking createBooking(Booking booking) {

        booking.setUser(
                userRepository.findById(booking.getUser().getId()).orElseThrow()
        );

        booking.setEvent(
                eventRepository.findById(booking.getEvent().getId()).orElseThrow()
        );

        booking.setSeat(
                seatRepository.findById(booking.getSeat().getId()).orElseThrow()
        );

        Seat seat = booking.getSeat();

        if (seat.getStatus() == SeatStatus.AVAILABLE) {

            seat.setStatus(SeatStatus.BOOKED);

            seatRepository.save(seat);

            return bookingRepository.save(booking);
        }

        throw new RuntimeException("Seat is already booked");
    }

}