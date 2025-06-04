package com.global.FloodWatch.dto;

import com.global.FloodWatch.model.Alerta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para resposta de dados de um Alerta")
public class AlertaResponseDTO {
    @Schema(description = "ID único do alerta.")
    private UUID id;
    @Schema(description = "ID do sensor que originou o alerta.")
    private UUID sensorId;
    @Schema(description = "Tipo do alerta.")
    private String tipo;
    @Schema(description = "Mensagem detalhada do alerta.")
    private String mensagem;
    @Schema(description = "Nível de severidade do alerta.")
    private Alerta.NivelAlerta nivel;
    @Schema(description = "Data e hora de criação do alerta.")
    private LocalDateTime criadoEm;
    @Schema(description = "Status de resolução do alerta ('S' para resolvido, 'N' para não resolvido).")
    private String resolvido;
}
