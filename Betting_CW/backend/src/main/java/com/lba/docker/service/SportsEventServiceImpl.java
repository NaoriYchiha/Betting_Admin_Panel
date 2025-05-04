package com.lba.docker.service;

import com.lba.docker.entity.EventType;
import com.lba.docker.entity.SportsEvent;
import com.lba.docker.repository.SportsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SportsEventServiceImpl implements SportsEventService {

    @Autowired
    private SportsEventRepository sportsEventRepository;

    @Override
    @Cacheable(value = "sportEvents", key = "'allEvents'")
    public List<SportsEvent> getAllEvents() {
        return sportsEventRepository.findAll();
    }

    @Override
    @Cacheable(value = "sportEventsByType", key = "#eventType")
    public List<SportsEvent> getEventsByType(EventType eventType) {
        return sportsEventRepository.findByEventType(eventType);
    }

    @Override
    @Cacheable(value = "sportEventById", key = "#eventId")
    public Optional<SportsEvent> getEventById(Long eventId) {
        return sportsEventRepository.findById(eventId);
    }

    @Override
    @Cacheable(value = "sportEventByName", key = "#name")
    public Optional<SportsEvent> getEventByName(String name) {
        return Optional.ofNullable(sportsEventRepository.findByName(name));
    }

    @Override
    @Cacheable(value = "sportEventInRange", key = "#startDate + '_' + #endDate")
    public List<SportsEvent> getEventsInRange(LocalDateTime startDate, LocalDateTime endDate) {
        return sportsEventRepository.findByEventDateBetween(startDate, endDate);
    }

    @Override
    @CacheEvict(value = {
            "sportEvents",
            "sportEventsByType",
            "sportEventById",
            "sportEventByName",
            "sportEventInRange"
    }, allEntries = true)
    public SportsEvent createEvent(SportsEvent event) {
        return sportsEventRepository.save(event);
    }

    @CacheEvict(value = {
            "sportEvents",
            "sportEventsByType",
            "sportEventById",
            "sportEventByName",
            "sportEventInRange"
    }, allEntries = true)
    @Override
    public SportsEvent updateEvent(SportsEvent event) {
        return sportsEventRepository.save(event);
    }

    @CacheEvict(value = {
            "sportEvents",
            "sportEventsByType",
            "sportEventById",
            "sportEventByName",
            "sportEventInRange"
    }, allEntries = true)
    @Override
    public void deleteEvent(Long eventId) {
        sportsEventRepository.deleteById(eventId);
    }
}
