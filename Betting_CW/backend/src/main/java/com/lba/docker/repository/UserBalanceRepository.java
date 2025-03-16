package com.lba.docker.repository;

import com.lba.docker.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
    // Найти баланс пользователя по ID
    UserBalance findByUserId(Long userId);

    // Найти все транзакции для пользователя
    List<UserBalance> findByUserIdOrderByTransactionDateDesc(Long userId);
}
