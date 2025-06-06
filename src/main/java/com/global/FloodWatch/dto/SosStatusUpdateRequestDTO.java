package com.global.FloodWatch.dto;

import com.global.FloodWatch.model.Sos;
import com.global.FloodWatch.model.StatusSos;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para atualização do status de um pedido de SOS")
public class SosStatusUpdateRequestDTO {
    @Schema(description = "Novo status para o pedido de SOS.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O status não pode ser nulo")
    private StatusSos status;
}


