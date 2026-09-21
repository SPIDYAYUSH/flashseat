package com.example.flashseat.flashseat.repo;

import com.example.flashseat.flashseat.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}