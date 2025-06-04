package com.global.FloodWatch.dto;

import com.global.FloodWatch.model.Drone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para requisição de criação ou atualização de Drone")
public class DroneRequestDTO {
    @Schema(description = "Nome ou identificador do drone.", example = "Drone Resgate Alpha", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O nome do drone não pode estar em branco")
    @Size(max = 50, message = "O nome do drone deve ter no máximo 50 caracteres")
    private String nome;

    @Schema(description = "Status atual do drone.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O status do drone não pode ser nulo")
    private Drone.StatusDrone status;

    @Schema(description = "Descrição da localização atual do drone.", example = "Base Operacional Central")
    @Size(max = 100, message = "A localização atual deve ter no máximo 100 caracteres")
    private String localAtual;

    @Schema(description = "Latitude da localização atual do drone.", example = "-22.9080")
    private Double latitude;

    @Schema(description = "Longitude da localização atual do drone.", example = "-43.1750")
    private Double longitude;
}