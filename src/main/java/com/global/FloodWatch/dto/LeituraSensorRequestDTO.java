package com.global.FloodWatch.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraSensorRequestDTO {
    @NotNull(message = "O ID do sensor não pode ser nulo")
    private UUID sensorId;

    @NotNull(message = "O valor da leitura não pode ser nulo")
    private Double valor;

    @Size(max = 10, message = "A unidade deve ter no máximo 10 caracteres")
    private String unidade;
}