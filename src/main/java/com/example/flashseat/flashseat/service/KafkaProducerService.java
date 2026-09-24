package com.example.flashseat.flashseat.service;

import com.example.flashseat.flashseat.model.BookingEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaProducerService(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {

        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendBookingEvent(BookingEvent bookingEvent) {

        try {
            String message = objectMapper.writeValueAsString(bookingEvent);

            kafkaTemplate.send("booking-events", message);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to convert booking event to JSON", e
            );
        }
    }
}