package com.global.FloodWatch.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertaResponseDTO {
    private UUID id;
    private UUID sensorId;
    private String tipo;
    private String mensagem;
    private com.global.FloodWatch.model.Alerta.NivelAlerta nivel;
    private LocalDateTime criadoEm;
    private String resolvido;
}
