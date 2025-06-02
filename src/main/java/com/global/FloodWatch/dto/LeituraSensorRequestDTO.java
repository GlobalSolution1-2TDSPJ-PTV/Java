package com.global.FloodWatch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para requisição de criação de uma Leitura de Sensor")
public class LeituraSensorRequestDTO {
    @Schema(description = "ID do sensor que realizou a leitura.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O ID do sensor não pode ser nulo")
    private UUID sensorId;

    @Schema(description = "Valor numérico da leitura.", example = "120.5", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O valor da leitura não pode ser nulo")
    private Double valor;

    @Schema(description = "Unidade de medida da leitura (ex: cm, mm, m/s).", example = "cm")
    @Size(max = 10, message = "A unidade deve ter no máximo 10 caracteres")
    private String unidade;
}