package com.global.FloodWatch.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneResponseDTO {
    private UUID id;
    private String nome;
    private com.global.FloodWatch.model.Drone.StatusDrone status;
    private String localAtual;
    private Double latitude;
    private Double longitude;
}
