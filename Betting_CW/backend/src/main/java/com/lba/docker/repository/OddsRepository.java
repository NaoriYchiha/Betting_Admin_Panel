package com.lba.docker.repository;

import com.lba.docker.entity.Odds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OddsRepository extends JpaRepository<Odds, Long> {
    // Найти коэффициенты для события
    List<Odds> findByEventId(Long eventId);

    // Найти коэффициент по значению
    Odds findByValue(double value);
}
