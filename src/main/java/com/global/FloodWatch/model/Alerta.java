package com.global.FloodWatch.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @Column(columnDefinition = "RAW(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    private String tipo;

    @Lob
    private String mensagem;

    @Enumerated(EnumType.STRING)
    private NivelAlerta nivel;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    private String resolvido; // 'S' ou 'N'

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
        criadoEm = LocalDateTime.now();
        resolvido = "N";
    }

    public enum NivelAlerta {
        baixo, moderado, critico
    }
}