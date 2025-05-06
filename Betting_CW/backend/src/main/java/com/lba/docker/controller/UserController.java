package com.lba.docker.controller;

import com.lba.docker.entity.User;
import com.lba.docker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if (userService.existsByUsername(user.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ошибка: имя пользователя уже занято!");
            }
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("1Ошибка при создании пользователя");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
           @RequestBody User newUserData) {

        return userService.findById(id)
                .map(existingUser -> {
                    // Проверка уникальности имени (если передано новое имя)
                    if (newUserData.getUsername() != null &&
                            !newUserData.getUsername().equals(existingUser.getUsername()) &&
                            userService.existsByUsername(newUserData.getUsername())) {
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body("Ошибка: имя пользователя уже занято!");
                    }

                    // Обновляем только переданные поля
                    if (newUserData.getUsername() != null) existingUser.setUsername(newUserData.getUsername());
                    if (newUserData.getPassword() != null) existingUser.setPassword(newUserData.getPassword());
                    if (newUserData.getRole() != null) existingUser.setRole(newUserData.getRole());

                    // Сохраняем обновленного пользователя
                    User updatedUser = userService.updateUser(existingUser);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser( @PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}