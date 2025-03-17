package com.lba.docker.service;

import com.lba.docker.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    public boolean existsByUsername(String username);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
}
