package com.lba.docker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Разрешаем CORS для всех API
                .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешенные HTTP методы
                .allowedHeaders("*") // Разрешаем все заголовки
                .allowCredentials(true); // Разрешаем использование куки
    }
}
