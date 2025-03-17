package com.lba.docker.repository;

import com.lba.docker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Поиск пользователя по имени
    User findByUsername(String username);
    boolean existsByUsername(String username);
}

