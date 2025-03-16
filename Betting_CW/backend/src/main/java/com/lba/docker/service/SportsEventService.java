package com.lba.docker.service;

import com.lba.docker.entity.SportsEvent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SportsEventService {
    List<SportsEvent> getAllEvents();
    List<SportsEvent> getEventsByType(SportsEvent.EventType eventType);
    Optional<SportsEvent> getEventById(Long eventId);
    Optional<SportsEvent> getEventByName(String name);
    List<SportsEvent> getEventsInRange(LocalDateTime startDate, LocalDateTime endDate);
    SportsEvent createEvent(SportsEvent event);
    SportsEvent updateEvent(SportsEvent event);
    void deleteEvent(Long eventId);
}
