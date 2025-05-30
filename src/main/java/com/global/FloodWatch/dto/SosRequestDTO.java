package com.global.FloodWatch.dto;


import com.global.FloodWatch.model.Usuario; // Importe o Enum
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class SosRequestDTO {
    @NotNull(message = "O ID do usuário não pode ser nulo")
    private UUID usuarioId; // Apenas o ID para criar a associação

    @NotNull(message = "A latitude não pode ser nula")
    private Double latitude;

    @NotNull(message = "A longitude não pode ser nula")
    private Double longitude;
}
