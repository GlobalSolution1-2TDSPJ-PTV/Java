package com.global.FloodWatch.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(nullable = false, length = 1)
    private String ativo; // 'S' ou 'N'

    @Column(name = "instalado_em")
    private LocalDateTime instaladoEm;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<LeituraSensor> leituras = new ArrayList<>();

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Alerta> alertas = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        this.instaladoEm = LocalDateTime.now();
    }

    public void addLeitura(LeituraSensor leitura) {
        leituras.add(leitura);
        leitura.setSensor(this);
    }

    public void removeLeitura(LeituraSensor leitura) {
        leituras.remove(leitura);
        leitura.setSensor(null);
    }

    public void addAlerta(Alerta alerta) {
        alertas.add(alerta);
        alerta.setSensor(this);
    }

    public void removeAlerta(Alerta alerta) {
        alertas.remove(alerta);
        alerta.setSensor(null);
    }
}