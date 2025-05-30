package com.global.FloodWatch.dto;

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
public class AlertaRequestDTO {
    @NotNull(message = "O ID do sensor não pode ser nulo")
    private UUID sensorId;

    @NotBlank(message = "O tipo do alerta não pode estar em branco")
    @Size(max = 50, message = "O tipo do alerta deve ter no máximo 50 caracteres")
    private String tipo;

    private String mensagem; // CLOB pode ser grande, validação de tamanho pode ser complexa aqui

    @NotNull(message = "O nível do alerta não pode ser nulo")
    private com.global.FloodWatch.model.Alerta.NivelAlerta nivel;
}
