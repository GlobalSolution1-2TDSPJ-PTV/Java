package com.global.FloodWatch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para resposta de dados de uma Leitura de Sensor")
public class LeituraSensorResponseDTO {
    @Schema(description = "ID único da leitura.")
    private UUID id;
    @Schema(description = "ID do sensor que realizou a leitura.")
    private UUID sensorId;
    @Schema(description = "Valor numérico da leitura.")
    private Double valor;
    @Schema(description = "Unidade de medida da leitura.")
    private String unidade;
    @Schema(description = "Data e hora em que a leitura foi realizada.")
    private LocalDateTime lidoEm;
}