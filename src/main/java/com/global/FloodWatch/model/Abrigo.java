package com.global.FloodWatch.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "abrigos")
public class Abrigo {

    @Id
    @Column(columnDefinition = "RAW(16)")
    private UUID id;

    private String nome;

    private Integer capacidade;

    @Column(name = "ocupacao_atual")
    private Integer ocupacaoAtual;

    private Double latitude;
    private Double longitude;

    private String responsavel;

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
        ocupacaoAtual = 0;
    }
}
