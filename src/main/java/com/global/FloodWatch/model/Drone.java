package com.global.FloodWatch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drones")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDrone status;

    @Column(name = "local_atual")
    private String localAtual;

    private Double latitude;
    private Double longitude;


    public enum StatusDrone {
        ativo, em_missao, offline
    }
}