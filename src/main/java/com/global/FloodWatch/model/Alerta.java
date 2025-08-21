package com.global.FloodWatch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id", nullable = false)
    @ToString.Exclude // Para evitar recursão com Sensor.toString()
    @EqualsAndHashCode.Exclude // Para evitar recursão com Sensor.equals/hashCode()
    private Sensor sensor;

    private String tipo;

    @Lob // Para campos CLOB no Oracle
    private String mensagem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelAlerta nivel;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(length = 1, nullable = false)
    private String resolvido; // 'S' ou 'N'

}