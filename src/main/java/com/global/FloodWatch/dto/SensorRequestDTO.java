package com.global.FloodWatch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para requisição de criação ou atualização de Sensor")
public class SensorRequestDTO {
    @Schema(description = "Descrição da localização do sensor.", example = "Ponte Rio-Niterói, Pilar 7", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "A localização não pode estar em branco")
    @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres")
    private String localizacao;

    @Schema(description = "Latitude da localização do sensor.", example = "-22.8974")
    private Double latitude;

    @Schema(description = "Longitude da localização do sensor.", example = "-43.1797")
    private Double longitude;

    @Schema(description = "Tipo do sensor (ex: Nível de Água, Pluviômetro).", example = "Nível de Água Ultrassônico", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O tipo do sensor não pode estar em branco")
    @Size(max = 50, message = "O tipo deve ter no máximo 50 caracteres")
    private String tipo;

    @Schema(description = "Status de atividade do sensor ('S' para ativo, 'N' para inativo).", example = "S", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O status de ativo não pode ser nulo")
    @Pattern(regexp = "^[SN]$", message = "Ativo deve ser 'S' ou 'N'")
    private String ativo;
}

