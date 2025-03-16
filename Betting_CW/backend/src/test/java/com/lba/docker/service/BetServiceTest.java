package com.lba.docker.service;

import com.lba.docker.entity.Bet;
import com.lba.docker.entity.SportsEvent;
import com.lba.docker.entity.User;
import com.lba.docker.repository.BetRepository;
import com.lba.docker.repository.SportsEventRepository;
import com.lba.docker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class BetServiceTest {

    @InjectMocks
    private BetServiceImpl betService;

    @Mock
    private BetRepository betRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SportsEventRepository sportsEventRepository;

    private Bet testBet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testBet = new Bet();
        testBet.setId(1L);
        testBet.setAmount(50.0);
    }

    @Test
    public void testPlaceBet() {
        // Мокируем зависимости
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(sportsEventRepository.findById(1L)).thenReturn(Optional.of(new SportsEvent()));

        Bet mockBet = new Bet();
        mockBet.setAmount(50.0);

        // Мокируем метод save, чтобы он возвращал mockBet
        when(betRepository.save(any(Bet.class))).thenReturn(mockBet);

        // Выполняем тест
        Bet placedBet = betService.placeBet(1L, 1L, 50.0, Bet.BetStatus.PENDING);
        assertEquals(50.0, placedBet.getAmount(), 0.001);
    }

    @Test
    public void testGetBetsByUser() {
        when(betRepository.findByUserId(1L)).thenReturn(List.of(testBet));

        List<Bet> bets = betService.getBetsByUser(1L);
        assertFalse(bets.isEmpty());
    }

    @Test
    public void testGetBetsByEvent() {
        when(betRepository.findByEventId(1L)).thenReturn(List.of(testBet));

        List<Bet> bets = betService.getBetsByEvent(1L);
        assertFalse(bets.isEmpty());
    }
}
