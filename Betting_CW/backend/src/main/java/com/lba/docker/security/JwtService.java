package com.lba.docker.security;

import com.lba.docker.config.JwtProperties;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import com.lba.docker.security.CustomUserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 часа

    // Генерация токена
    public String generateToken(CustomUserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
    }

    // Валидация токена
    public boolean validateToken(String token, CustomUserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Извлечение имени пользователя из токена
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Проверка истечения срока действия токена
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Извлечение всех клеймов из токена
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            // Обрабатываем ошибку при парсинге токена (например, если токен повреждён или подпись неверная)
            return null;  // Возвращаем null, если токен невалиден
        }
    }
}
