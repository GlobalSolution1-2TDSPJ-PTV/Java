package com.global.FloodWatch.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank
        String email,
        String password,
        String refreshToken
) {
}
