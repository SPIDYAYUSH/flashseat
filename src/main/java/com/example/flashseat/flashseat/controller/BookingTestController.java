package com.example.flashseat.flashseat.controller;

import com.example.flashseat.flashseat.client.BookingClient;
import com.example.flashseat.flashseat.model.Booking;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test-booking")
public class BookingTestController {

    private final BookingClient bookingClient;

    public BookingTestController(BookingClient bookingClient) {
        this.bookingClient = bookingClient;
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingClient.createBooking(booking);
    }
}