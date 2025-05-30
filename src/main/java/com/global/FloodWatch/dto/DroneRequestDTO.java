package com.global.FloodWatch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneRequestDTO {
    @NotBlank(message = "O nome do drone não pode estar em branco")
    @Size(max = 50, message = "O nome do drone deve ter no máximo 50 caracteres")
    private String nome;

    @NotNull(message = "O status do drone não pode ser nulo")
    private com.global.FloodWatch.model.Drone.StatusDrone status;

    @Size(max = 100, message = "A localização atual deve ter no máximo 100 caracteres")
    private String localAtual;

    private Double latitude;
    private Double longitude;
}
