package com.lba.docker.service;

import com.lba.docker.entity.EventType;
import com.lba.docker.entity.SportsEvent;
import com.lba.docker.repository.SportsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SportsEventServiceImpl implements SportsEventService {

    @Autowired
    private SportsEventRepository sportsEventRepository;

    @Override
    public List<SportsEvent> getAllEvents() {
        return sportsEventRepository.findAll();
    }

    @Override
    public List<SportsEvent> getEventsByType(EventType eventType) {
        return sportsEventRepository.findByEventType(eventType);
    }

    @Override
    public Optional<SportsEvent> getEventById(Long eventId) {
        return sportsEventRepository.findById(eventId);
    }

    @Override
    public Optional<SportsEvent> getEventByName(String name) {
        return Optional.ofNullable(sportsEventRepository.findByName(name));
    }

    @Override
    public List<SportsEvent> getEventsInRange(LocalDateTime startDate, LocalDateTime endDate) {
        return sportsEventRepository.findByEventDateBetween(startDate, endDate);
    }

    @Override
    public SportsEvent createEvent(SportsEvent event) {
        return sportsEventRepository.save(event);
    }

    @Override
    public SportsEvent updateEvent(SportsEvent event) {
        return sportsEventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        sportsEventRepository.deleteById(eventId);
    }
}
