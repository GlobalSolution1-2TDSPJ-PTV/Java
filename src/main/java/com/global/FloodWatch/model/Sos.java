package com.global.FloodWatch.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sos")
public class Sos {

    @Id
    @Column(columnDefinition = "RAW(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private StatusSos status;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
        criadoEm = LocalDateTime.now();
        status = StatusSos.pendente;
    }

    public enum StatusSos {
        pendente, em_atendimento, resolvido
    }
}
