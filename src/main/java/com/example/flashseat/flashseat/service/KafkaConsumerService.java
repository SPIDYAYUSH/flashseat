package com.example.flashseat.flashseat.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(
            topics = "booking-events",
            groupId = "flashseat-group"
    )
    public void consumeBookingEvent(String message) {

        System.out.println("Received Booking Event: " + message);
    }
}