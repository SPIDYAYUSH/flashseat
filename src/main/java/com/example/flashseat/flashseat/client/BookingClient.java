package com.example.flashseat.flashseat.client;

import com.example.flashseat.flashseat.model.Booking;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "booking-service",
        url = "http://localhost:8081"
)
public interface BookingClient {

    @PostMapping("/bookings")
    Booking createBooking(@RequestBody Booking booking);

    @GetMapping("/bookings")
    List<Booking> getAllBookings();

    @GetMapping("/bookings/{id}")
    Booking getBookingById(@PathVariable Long id);
}