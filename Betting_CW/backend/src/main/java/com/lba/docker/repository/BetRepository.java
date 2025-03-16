package com.lba.docker.repository;

import com.lba.docker.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    // Найти все ставки по пользователю
    List<Bet> findByUserId(Long userId);

    // Найти все ставки для события
    List<Bet> findByEventId(Long eventId);

    // Найти ставки по статусу
    List<Bet> findByStatus(Bet.BetStatus status);
}

