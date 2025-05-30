package com.global.FloodWatch.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SosStatusUpdateRequestDTO {
    @NotNull(message = "O status não pode ser nulo")
    private com.global.FloodWatch.model.Sos.StatusSos status;
}

