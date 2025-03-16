package com.lba.docker.service;

import com.lba.docker.entity.UserBalance;
import com.lba.docker.repository.UserBalanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserBalanceServiceTest {

    @InjectMocks
    private UserBalanceServiceImpl userBalanceService;

    @Mock
    private UserBalanceRepository userBalanceRepository;

    private UserBalance userBalance;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userBalance = new UserBalance();
        userBalance.setId(1L);
        userBalance.setAmount(100.0);
    }

    @Test
    public void testCreateTransaction() {
        when(userBalanceRepository.save(userBalance)).thenReturn(userBalance);

        UserBalance createdBalance = userBalanceService.createTransaction(userBalance);
        assertEquals(userBalance.getAmount(), createdBalance.getAmount());
    }

    @Test
    public void testFindByUserId() {
        when(userBalanceRepository.findByUserId(1L)).thenReturn(userBalance);

        UserBalance foundBalance = userBalanceService.findByUserId(1L);
        assertEquals(100.0, foundBalance.getAmount());
    }

    @Test
    public void testFindTransactionsByUserId() {
        when(userBalanceRepository.findByUserIdOrderByTransactionDateDesc(1L)).thenReturn(List.of(userBalance));

        List<UserBalance> balances = userBalanceService.findTransactionsByUserId(1L);
        assertFalse(balances.isEmpty());
    }
}
