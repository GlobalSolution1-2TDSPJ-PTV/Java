package com.global.FloodWatch.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertaResolvidoUpdateRequestDTO {
    @NotNull(message = "O status de resolvido não pode ser nulo")
    @Pattern(regexp = "^[SN]$", message = "Resolvido deve ser 'S' ou 'N'")
    private String resolvido;
}
