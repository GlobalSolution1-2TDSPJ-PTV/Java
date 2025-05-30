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
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        if (this.ocupacaoAtual == null) {
            this.ocupacaoAtual = 0;
        }
    }
}
