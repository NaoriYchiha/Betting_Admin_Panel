package com.lba.docker.controller;

import com.lba.docker.entity.User;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private User.Role role;
}