package com.lba.docker.service;

import com.lba.docker.entity.User;
import com.lba.docker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // Кэширование при поиске по ID
    @Cacheable(value = "users", key = "#id")
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Кэширование при поиске по имени пользователя
    @Cacheable(value = "usersByUsername", key = "#username")
    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Сбросить кэш при создании пользователя
    @CacheEvict(value = {
            "users",
            "usersByUsername"
    }, allEntries = true)
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Сбросить кэш при обновлении пользователя
    @CacheEvict(value = {
            "users",
            "usersByUsername"
    }, allEntries = true)
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Сбросить кэш при удалении пользователя
    @CacheEvict(value = {
            "users",
            "usersByUsername"
    }, allEntries = true)
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
