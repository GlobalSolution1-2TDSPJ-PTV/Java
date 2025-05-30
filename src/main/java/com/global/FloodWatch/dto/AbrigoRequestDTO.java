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
public class AbrigoRequestDTO {
    @NotBlank(message = "O nome do abrigo não pode estar em branco")
    @Size(max = 100, message = "O nome do abrigo deve ter no máximo 100 caracteres")
    private String nome;

    @NotNull(message = "A capacidade não pode ser nula")
    private Integer capacidade;

    // ocupacaoAtual geralmente é gerenciada pelo sistema, não enviada na criação/update total
    // Mas pode ser útil para um update específico de ocupação.
    private Integer ocupacaoAtual;

    private Double latitude;
    private Double longitude;

    @Size(max = 100, message = "O nome do responsável deve ter no máximo 100 caracteres")
    private String responsavel;
}
