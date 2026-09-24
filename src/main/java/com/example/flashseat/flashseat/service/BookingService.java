package com.example.flashseat.flashseat.service;

import com.example.flashseat.flashseat.Exception.SeatAlreadyBookedException;
import com.example.flashseat.flashseat.model.Booking;
import com.example.flashseat.flashseat.model.BookingEvent;
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
    private final SeatLockService seatLockService;
    private final KafkaProducerService kafkaProducerService;

    public BookingService(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            EventRepository eventRepository,
            SeatRepository seatRepository,
            SeatLockService seatLockService,
            KafkaProducerService kafkaProducerService) {

        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.seatRepository = seatRepository;
        this.seatLockService = seatLockService;
        this.kafkaProducerService = kafkaProducerService;
    }

    public Booking createBooking(Booking booking) {

        // Get IDs before replacing the objects
        Long userId = booking.getUser().getId();
        Long eventId = booking.getEvent().getId();
        Long seatId = booking.getSeat().getId();

        // Load User from database
        booking.setUser(
                userRepository.findById(userId).orElseThrow()
        );

        // Load Event from database
        booking.setEvent(
                eventRepository.findById(eventId).orElseThrow()
        );

        // Load Seat from database
        booking.setSeat(
                seatRepository.findById(seatId).orElseThrow()
        );

        Seat seat = booking.getSeat();

        // Check seat availability
        if (seat.getStatus() == SeatStatus.AVAILABLE) {

            // Mark seat as booked
            seat.setStatus(SeatStatus.BOOKED);
            seatRepository.save(seat);

            // Save booking
            Booking savedBooking = bookingRepository.save(booking);

            // Create Kafka event
            BookingEvent bookingEvent = new BookingEvent(
                    savedBooking.getId(),
                    userId,
                    eventId,
                    seatId
            );

            // Send booking event to Kafka
            kafkaProducerService.sendBookingEvent(bookingEvent);

            return savedBooking;
        }

        throw new SeatAlreadyBookedException("Seat is already booked");
    }
}