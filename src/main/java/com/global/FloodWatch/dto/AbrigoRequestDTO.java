package com.global.FloodWatch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para requisição de criação ou atualização de Abrigo")
public class AbrigoRequestDTO {
    @Schema(description = "Nome do abrigo.", example = "Abrigo Comunitário Esperança", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O nome do abrigo não pode estar em branco")
    @Size(max = 100, message = "O nome do abrigo deve ter no máximo 100 caracteres")
    private String nome;

    @Schema(description = "Capacidade máxima de pessoas no abrigo.", example = "150", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "A capacidade não pode ser nula")
    @Min(value = 1, message = "Capacidade deve ser no mínimo 1")
    private Integer capacidade;

    @Schema(description = "Ocupação atual do abrigo. Opcional na criação, padrão 0.", example = "75")
    @Min(value = 0, message = "Ocupação atual não pode ser negativa")
    private Integer ocupacaoAtual;

    @Schema(description = "Latitude da localização do abrigo.", example = "-22.9110")
    private Double latitude;

    @Schema(description = "Longitude da localização do abrigo.", example = "-43.2094")
    private Double longitude;

    @Schema(description = "Nome do responsável pelo abrigo.", example = "Carlos Andrade")
    @Size(max = 100, message = "O nome do responsável deve ter no máximo 100 caracteres")
    private String responsavel;
}
