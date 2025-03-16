package com.lba.docker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private SportsEvent event;

    @ManyToOne
    @JoinColumn(name = "odds_id", nullable = false)
    private Odds odds;

    @Column(nullable = false)
    private double amount; // Сумма ставки

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BetStatus status;

    @Column(nullable = false)
    private LocalDateTime betDate; // Дата ставки

    public enum BetStatus {
        WIN, LOSE, PENDING
    }

    // Геттеры и сеттеры
}
