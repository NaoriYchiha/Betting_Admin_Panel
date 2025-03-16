package com.lba.docker.service;

import com.lba.docker.entity.TransactionLog;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionLogService {
    List<TransactionLog> getTransactionLogsByUser(Long userId);
    List<TransactionLog> getTransactionLogsByType(TransactionLog.TransactionType type);
    List<TransactionLog> getTransactionLogsInRange(LocalDateTime startDate, LocalDateTime endDate);
    TransactionLog createTransactionLog(TransactionLog transactionLog);
}
