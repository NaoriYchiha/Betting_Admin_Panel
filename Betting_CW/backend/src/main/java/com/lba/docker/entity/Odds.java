package com.lba.docker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "odds")
public class Odds implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double value; // Значение коэффициента

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private SportsEvent event; // Событие, к которому относится коэффициент

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OutcomeType outcomeType;

}

