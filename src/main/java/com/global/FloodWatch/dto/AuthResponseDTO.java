package com.global.FloodWatch.dto;

public record AuthResponseDTO(
        String token,
        String refreshToken
) {
}
