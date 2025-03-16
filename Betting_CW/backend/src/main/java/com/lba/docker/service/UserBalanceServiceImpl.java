package com.lba.docker.service;

import com.lba.docker.entity.UserBalance;
import com.lba.docker.repository.UserBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBalanceServiceImpl implements UserBalanceService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Override
    public UserBalance findByUserId(Long userId) {
        return userBalanceRepository.findByUserId(userId);
    }

    @Override
    public List<UserBalance> findTransactionsByUserId(Long userId) {
        return userBalanceRepository.findByUserIdOrderByTransactionDateDesc(userId);
    }

    @Override
    public UserBalance createTransaction(UserBalance transaction) {
        return userBalanceRepository.save(transaction);
    }

    @Override
    public UserBalance updateTransaction(UserBalance transaction) {
        return userBalanceRepository.save(transaction);
    }
}
