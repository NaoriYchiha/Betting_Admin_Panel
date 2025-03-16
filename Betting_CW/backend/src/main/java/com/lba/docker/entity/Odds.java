package com.lba.docker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "odds")
public class Odds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double value; // Значение коэффициента

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private SportsEvent event; // Событие, к которому относится коэффициент

    // Геттеры и сеттеры
}

