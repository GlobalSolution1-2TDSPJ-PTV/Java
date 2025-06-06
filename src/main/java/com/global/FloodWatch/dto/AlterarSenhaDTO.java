package com.global.FloodWatch.dto;

import jakarta.validation.constraints.NotBlank;

public record AlterarSenhaDTO(
        @NotBlank String novaSenha
) {}

