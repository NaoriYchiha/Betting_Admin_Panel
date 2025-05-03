package com.lba.docker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sports_events")
public class SportsEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Название события (например, матч "Команда 1 vs Команда 2")

    @Column(nullable = false)
    private LocalDateTime eventDate; // Дата и время события

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType eventType; // Тип события (например, CS:GO, Football)

}
