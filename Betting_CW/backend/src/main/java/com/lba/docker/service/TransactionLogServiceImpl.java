package com.lba.docker.service;

import com.lba.docker.entity.TransactionLog;
import com.lba.docker.repository.TransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionLogServiceImpl implements TransactionLogService {

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Override
    public List<TransactionLog> getTransactionLogsByUser(Long userId) {
        return transactionLogRepository.findByUserId(userId);
    }

    @Override
    public List<TransactionLog> getTransactionLogsByType(TransactionLog.TransactionType type) {
        return transactionLogRepository.findByType(type);
    }

    @Override
    public List<TransactionLog> getTransactionLogsInRange(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionLogRepository.findByTransactionDateBetween(startDate, endDate);
    }

    @Override
    public TransactionLog createTransactionLog(TransactionLog transactionLog) {
        return transactionLogRepository.save(transactionLog);
    }
}
