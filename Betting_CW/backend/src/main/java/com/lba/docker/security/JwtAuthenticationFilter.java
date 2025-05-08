package com.lba.docker.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MyUserDetailService userDetailService;

    public JwtAuthenticationFilter(JwtService jwtService, MyUserDetailService userDetailService) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String token = extractToken(request);

        if (token != null && jwtService.validateToken(token, getUserDetails(token))) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);  // Extracts the token
        }
        return null;
    }

    private CustomUserDetails getUserDetails(String token) {
        String username = jwtService.extractUsername(token);
        CustomUserDetails userDetails = (CustomUserDetails) userDetailService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return userDetails;
    }

    private JwtAuthentication getAuthentication(String token) {
        CustomUserDetails userDetails = getUserDetails(token);
        return new JwtAuthentication(userDetails);
    }
}
