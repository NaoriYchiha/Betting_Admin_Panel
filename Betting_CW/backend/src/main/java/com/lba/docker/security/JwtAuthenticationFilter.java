package com.lba.docker.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;
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
        // Извлекаем токен из куки
        String token = extractTokenFromCookie(request);

        if (token != null) {
            CustomUserDetails userDetails = getUserDetails(token);

            if (userDetails != null && jwtService.validateToken(token, userDetails)) {
                SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(userDetails));
            }
        }

        filterChain.doFilter(request, response);
    }

    // Метод извлечения токена из куки
    private String extractTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Получаем данные пользователя из токена
    private CustomUserDetails getUserDetails(String token) {
        try {
            String username = jwtService.extractUsername(token);
            return (CustomUserDetails) userDetailService.loadUserByUsername(username);
        } catch (Exception e) {
            return null;  // Если произошла ошибка при получении пользователя, возвращаем null
        }
    }
}
