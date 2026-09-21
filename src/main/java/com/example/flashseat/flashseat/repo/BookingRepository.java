package com.example.flashseat.flashseat.repo;

import com.example.flashseat.flashseat.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}