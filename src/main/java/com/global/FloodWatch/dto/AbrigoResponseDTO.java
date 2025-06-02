package com.global.FloodWatch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para resposta de dados de Abrigo")
public class AbrigoResponseDTO {
    @Schema(description = "ID único do abrigo.")
    private UUID id;
    @Schema(description = "Nome do abrigo.")
    private String nome;
    @Schema(description = "Capacidade máxima de pessoas no abrigo.")
    private Integer capacidade;
    @Schema(description = "Ocupação atual do abrigo.")
    private Integer ocupacaoAtual;
    @Schema(description = "Latitude da localização do abrigo.")
    private Double latitude;
    @Schema(description = "Longitude da localização do abrigo.")
    private Double longitude;
    @Schema(description = "Nome do responsável pelo abrigo.")
    private String responsavel;
}
