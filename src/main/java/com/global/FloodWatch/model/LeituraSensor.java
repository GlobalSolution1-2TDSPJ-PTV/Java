package com.global.FloodWatch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leituras_sensor")
public class LeituraSensor {

    @Id
    @Column(columnDefinition = "RAW(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    private Double valor;

    private String unidade;

    @Column(name = "lido_em")
    private LocalDateTime lidoEm;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        this.lidoEm = LocalDateTime.now();
    }
}