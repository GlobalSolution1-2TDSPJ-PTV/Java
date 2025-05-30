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
    @Column(columnDefinition = "RAW(16)")
    private UUID id;

    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDrone status;

    @Column(name = "local_atual")
    private String localAtual;

    private Double latitude;
    private Double longitude;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        if (this.status == null) {
            this.status = StatusDrone.ativo;
        }
    }

    public enum StatusDrone {
        ativo, em_missao, offline
    }
}