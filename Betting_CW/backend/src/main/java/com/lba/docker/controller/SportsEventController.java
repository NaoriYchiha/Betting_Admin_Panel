package com.lba.docker.controller;


import com.lba.docker.entity.EventType;
import com.lba.docker.entity.SportsEvent;
import com.lba.docker.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sportsEvent")
public class SportsEventController {

    @Autowired
    public SportsEventService sportsEventService;

    @GetMapping("/{eventType}")

    public ResponseEntity<List<SportsEvent>> getSportsEventByType(
            @PathVariable String eventType) {
        try {
            EventType eventTypeStr = EventType.valueOf(eventType.toUpperCase()); // Преобразование строки в enum
            List<SportsEvent> sportsEvents = sportsEventService.getEventsByType(eventTypeStr);

            if (sportsEvents.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(sportsEvents);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Если передан неверный тип события
        }
    }

    @PostMapping
    public ResponseEntity<SportsEvent> addSportEvent(
        @RequestBody SportsEvent sportsEvent) {
        SportsEvent createdSportEvent = sportsEventService.createEvent(sportsEvent);
        return new ResponseEntity<>(createdSportEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SportsEvent> updateSportEvent(
            @PathVariable Long id,
            @RequestBody SportsEvent newEventData) {

        return sportsEventService.getEventById(id)
                .map(existingEvent -> {
                    // Обновляем только непустые поля
                    if (newEventData.getName() != null) existingEvent.setName(newEventData.getName());
                    if (newEventData.getEventDate() != null) existingEvent.setEventDate(newEventData.getEventDate());
                    if (newEventData.getEventType() != null) existingEvent.setEventType(newEventData.getEventType());

                    // Сохраняем обновленный объект
                    SportsEvent updatedEvent = sportsEventService.updateEvent(existingEvent);
                    return ResponseEntity.ok(updatedEvent);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSportEvent(
           @PathVariable Long id) {
        Optional<SportsEvent> sportsEvent = sportsEventService.getEventById(id);
        if (sportsEvent.isPresent()) {
            sportsEventService.deleteEvent(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}