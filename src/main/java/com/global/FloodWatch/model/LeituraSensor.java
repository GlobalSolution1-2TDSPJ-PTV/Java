package com.global.FloodWatch.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "leituras_sensor")
public class LeituraSensor {

    @Id
    @Column(columnDefinition = "RAW(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    private Double valor;

    private String unidade;

    @Column(name = "lido_em")
    private LocalDateTime lidoEm;

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
        lidoEm = LocalDateTime.now();
    }
}