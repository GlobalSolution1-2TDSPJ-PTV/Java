package com.global.FloodWatch.dto;


import com.global.FloodWatch.model.Drone;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para resposta de dados de Drone")
public class DroneResponseDTO {
    @Schema(description = "ID único do drone.")
    private UUID id;
    @Schema(description = "Nome ou identificador do drone.")
    private String nome;
    @Schema(description = "Status atual do drone.")
    private Drone.StatusDrone status;
    @Schema(description = "Descrição da localização atual do drone.")
    private String localAtual;
    @Schema(description = "Latitude da localização atual do drone.")
    private Double latitude;
    @Schema(description = "Longitude da localização atual do drone.")
    private Double longitude;
}