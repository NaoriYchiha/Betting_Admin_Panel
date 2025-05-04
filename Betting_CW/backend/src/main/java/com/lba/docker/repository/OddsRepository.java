package com.lba.docker.repository;

import com.lba.docker.entity.Odds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OddsRepository extends JpaRepository<Odds, Long> {
    // Найти коэффициенты для события
    List<Odds> findByEventId(Long eventId);

    Optional<Odds> findById(Long id);

    // Найти коэффициент по значению
    Optional<Odds>  findByValue(double value);
}
