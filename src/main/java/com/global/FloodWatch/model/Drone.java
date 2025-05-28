package com.global.FloodWatch.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "drones")
public class Drone {

    @Id
    @Column(columnDefinition = "RAW(16)")
    private UUID id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private StatusDrone status;

    @Column(name = "local_atual")
    private String localAtual;

    private Double latitude;
    private Double longitude;

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
        status = StatusDrone.ativo;
    }

    public enum StatusDrone {
        ativo, em_missao, offline
    }
}
