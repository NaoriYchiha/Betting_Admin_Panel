package com.lba.docker.service;

import com.lba.docker.entity.Bet;
import com.lba.docker.entity.SportsEvent;
import com.lba.docker.entity.User;
import com.lba.docker.repository.BetRepository;
import com.lba.docker.repository.SportsEventRepository;
import com.lba.docker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SportsEventRepository sportsEventRepository;

    @Override
    public List<Bet> getBetsByUser(Long userId) {
        return betRepository.findByUserId(userId);
    }

    @Override
    public List<Bet> getBetsByEvent(Long eventId) {
        return betRepository.findByEventId(eventId);
    }

    @Override
    public List<Bet> getBetsByStatus(Bet.BetStatus status) {
        return betRepository.findByStatus(status);
    }

    public Bet placeBet(Long userId, Long eventId, double amount, Bet.BetStatus status) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        SportsEvent event = sportsEventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));

        Bet bet = new Bet();
        bet.setUser(user);
        bet.setEvent(event);
        bet.setAmount(amount);
        bet.setStatus(status);

        return betRepository.save(bet);  // Сохраняем и возвращаем
    }

    @Override
    public Bet updateBetStatus(Long betId, Bet.BetStatus status) {
        Bet bet = betRepository.findById(betId).orElseThrow(() -> new RuntimeException("Bet not found"));
        bet.setStatus(status);
        return betRepository.save(bet);
    }

    @Override
    public void deleteBet(Long betId) {
        betRepository.deleteById(betId);
    }
}

