package com.lba.docker.repository;

import com.lba.docker.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {
    // Найти все транзакции для пользователя
    List<TransactionLog> findByUserId(Long userId);

    // Найти все транзакции по типу
    List<TransactionLog> findByType(TransactionLog.TransactionType type);

    // Найти все транзакции для определенного диапазона дат
    List<TransactionLog> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
