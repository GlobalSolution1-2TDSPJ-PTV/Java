package com.global.FloodWatch.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraSensorResponseDTO {
    private UUID id;
    private UUID sensorId;
    private Double valor;
    private String unidade;
    private LocalDateTime lidoEm;
}