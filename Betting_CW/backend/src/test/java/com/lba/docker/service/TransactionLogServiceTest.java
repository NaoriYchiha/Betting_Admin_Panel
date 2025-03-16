package com.lba.docker.service;

import com.lba.docker.entity.TransactionLog;
import com.lba.docker.repository.TransactionLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TransactionLogServiceTest {

    @InjectMocks
    private TransactionLogServiceImpl transactionLogService;

    @Mock
    private TransactionLogRepository transactionLogRepository;

    private TransactionLog testTransaction;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testTransaction = new TransactionLog();
        testTransaction.setId(1L);
        testTransaction.setAmount(100.0);
    }

    @Test
    public void testCreateTransactionLog() {
        when(transactionLogRepository.save(testTransaction)).thenReturn(testTransaction);

        TransactionLog createdTransaction = transactionLogService.createTransactionLog(testTransaction);
        assertEquals(testTransaction.getAmount(), createdTransaction.getAmount());
    }

    @Test
    public void testGetTransactionLogsByUser() {
        when(transactionLogRepository.findByUserId(1L)).thenReturn(List.of(testTransaction));

        List<TransactionLog> transactions = transactionLogService.getTransactionLogsByUser(1L);
        assertFalse(transactions.isEmpty());
    }

    @Test
    public void testGetTransactionLogsByType() {
        when(transactionLogRepository.findByType(TransactionLog.TransactionType.DEPOSIT))
                .thenReturn(List.of(testTransaction));

        List<TransactionLog> transactions = transactionLogService.getTransactionLogsByType(TransactionLog.TransactionType.DEPOSIT);
        assertFalse(transactions.isEmpty());
    }
}

