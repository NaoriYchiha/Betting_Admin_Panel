package com.lba.docker.repository;

import com.lba.docker.entity.SportsEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SportsEventRepository extends JpaRepository<SportsEvent, Long> {
    // Найти все события по типу
    List<SportsEvent> findByEventType(SportsEvent.EventType eventType);

    // Найти событие по названию
    SportsEvent findByName(String name);

    // Найти события в диапазоне дат
    List<SportsEvent> findByEventDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
