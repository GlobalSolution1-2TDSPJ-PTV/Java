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
@Schema(description = "DTO para resposta de dados de Sensor")
public class SensorResponseDTO {
    @Schema(description = "ID único do sensor.")
    private UUID id;
    @Schema(description = "Descrição da localização do sensor.")
    private String localizacao;
    @Schema(description = "Latitude da localização do sensor.")
    private Double latitude;
    @Schema(description = "Longitude da localização do sensor.")
    private Double longitude;
    @Schema(description = "Tipo do sensor.")
    private String tipo;
    @Schema(description = "Status de atividade do sensor ('S' ou 'N').")
    private String ativo;
    @Schema(description = "Data e hora de instalação do sensor.")
    private LocalDateTime instaladoEm;
}
