package com.global.FloodWatch.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbrigoResponseDTO {
    private UUID id;
    private String nome;
    private Integer capacidade;
    private Integer ocupacaoAtual;
    private Double latitude;
    private Double longitude;
    private String responsavel;
}
