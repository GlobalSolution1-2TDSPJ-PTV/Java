package com.global.FloodWatch.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para requisição de criação de um pedido de SOS")
public class SosRequestDTO {

    @Schema(description = "Latitude da localização do pedido de SOS.", example = "-22.90278", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "A latitude não pode ser nula")
    private Double latitude;

    @Schema(description = "Longitude da localização do pedido de SOS.", example = "-43.2075", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "A longitude não pode ser nula")
    private Double longitude;
}
