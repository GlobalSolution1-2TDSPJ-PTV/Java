package com.global.FloodWatch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para atualização do status de resolução de um Alerta")
public class AlertaResolvidoUpdateRequestDTO {
    @Schema(description = "Novo status de resolução ('S' ou 'N').", example = "S", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O status de resolvido não pode ser nulo")
    @Pattern(regexp = "^[SN]$", message = "Resolvido deve ser 'S' ou 'N'")
    private String resolvido;
}
