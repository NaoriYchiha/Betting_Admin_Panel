package com.lba.docker.controller;


import com.lba.docker.security.JwtService;
import com.lba.docker.security.MyUserDetailService;
import com.lba.docker.entity.User;
import com.lba.docker.repository.UserRepository;
import com.lba.docker.security.CustomUserDetails;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"},
        allowCredentials = "true")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            var userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(request.getUsername());
            var token = jwtService.generateToken(userDetails);

            ResponseCookie cookie = ResponseCookie.from("token", token)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(60 * 60 * 24) // 24 час
                    .sameSite("Strict")
                    .build();

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body("Successfully login");

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());

        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkToken(HttpServletRequest request) {
        String token = extractTokenFromCookies(request);

        if (token != null) {
            String username = jwtService.extractUsername(token);
            var userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {
                return ResponseEntity.ok().body("Authorized as: " + username);
            }
        }

        return ResponseEntity.status(401).body("Unauthorized");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie deleteCookie = ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .body("Logged out");
    }

    private String extractTokenFromCookies(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        Optional<Cookie> tokenCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .findFirst();

        return tokenCookie.map(Cookie::getValue).orElse(null);
    }
}
