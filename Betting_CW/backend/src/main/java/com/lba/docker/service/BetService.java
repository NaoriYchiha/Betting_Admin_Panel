package com.lba.docker.service;

import com.lba.docker.entity.Bet;

import java.util.List;

public interface BetService {
    List<Bet> getBetsByUser(Long userId);
    List<Bet> getBetsByEvent(Long eventId);
    List<Bet> getBetsByStatus(Bet.BetStatus status);
    Bet placeBet(Long userId, Long eventId, double amount, Bet.BetStatus status);
    Bet updateBetStatus(Long betId, Bet.BetStatus status);
    void deleteBet(Long betId);
}
