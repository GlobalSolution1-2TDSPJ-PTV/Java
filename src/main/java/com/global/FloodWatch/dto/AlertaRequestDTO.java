package com.global.FloodWatch.dto;

import com.global.FloodWatch.model.Alerta;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para requisição de criação de um Alerta")
public class AlertaRequestDTO {
    @Schema(description = "ID do sensor que originou o alerta.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O ID do sensor não pode ser nulo")
    private UUID sensorId;

    @Schema(description = "Tipo do alerta (ex: Nível Alto, Falha no Sensor).", example = "Nível de Água Elevado", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O tipo do alerta não pode estar em branco")
    @Size(max = 50, message = "O tipo do alerta deve ter no máximo 50 caracteres")
    private String tipo;

    @Schema(description = "Mensagem detalhada do alerta.", example = "Nível da água atingiu 250cm, risco de transbordamento.")
    private String mensagem;

    @Schema(description = "Nível de severidade do alerta.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O nível do alerta não pode ser nulo")
    private Alerta.NivelAlerta nivel;
}
