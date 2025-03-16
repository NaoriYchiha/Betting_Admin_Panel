package com.lba.docker.service;

import com.lba.docker.entity.UserBalance;

import java.util.List;

public interface UserBalanceService {
    UserBalance findByUserId(Long userId);
    List<UserBalance> findTransactionsByUserId(Long userId);
    UserBalance createTransaction(UserBalance transaction);
    UserBalance updateTransaction(UserBalance transaction);
}
