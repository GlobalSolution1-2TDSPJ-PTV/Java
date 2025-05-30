package com.global.FloodWatch.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorRequestDTO {
    @NotBlank(message = "A localização não pode estar em branco")
    @Size(max = 100, message = "A localização deve ter no máximo 100 caracteres")
    private String localizacao;

    private Double latitude;
    private Double longitude;

    @NotBlank(message = "O tipo do sensor não pode estar em branco")
    @Size(max = 50, message = "O tipo deve ter no máximo 50 caracteres")
    private String tipo;

    @NotNull(message = "O status de ativo não pode ser nulo")
    @Pattern(regexp = "^[SN]$", message = "Ativo deve ser 'S' ou 'N'")
    private String ativo;
}

