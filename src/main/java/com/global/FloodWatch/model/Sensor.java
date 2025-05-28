package com.global.FloodWatch.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sensores")
public class Sensor {

    @Id
    @Column(columnDefinition = "RAW(16)")
    private UUID id;

    private String localizacao;

    private Double latitude;
    private Double longitude;

    private String tipo;

    @Column(nullable = false)
    private String ativo; // 'S' ou 'N'

    @Column(name = "instalado_em")
    private LocalDateTime instaladoEm;

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
        instaladoEm = LocalDateTime.now();
    }
}
