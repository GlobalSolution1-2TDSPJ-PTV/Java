package com.global.FloodWatch.controller;

import com.global.FloodWatch.dto.LeituraSensorRequestDTO;
import com.global.FloodWatch.dto.LeituraSensorResponseDTO;
import com.global.FloodWatch.service.LeituraSensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/leituras-sensor")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Leituras de Sensores", description = "APIs para gerenciamento de leituras de sensores")
public class LeituraSensorController {

    private final LeituraSensorService leituraSensorService;

    @Autowired
    public LeituraSensorController(LeituraSensorService leituraSensorService) {
        this.leituraSensorService = leituraSensorService;
    }

    @Operation(summary = "Registra uma nova leitura de sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Leitura registrada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LeituraSensorResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Sensor associado não encontrado")
    })
    @PostMapping
    public ResponseEntity<LeituraSensorResponseDTO> criarLeituraSensor(@Valid @RequestBody LeituraSensorRequestDTO leituraRequestDTO) {
        LeituraSensorResponseDTO novaLeitura = leituraSensorService.criarLeituraSensor(leituraRequestDTO);
        return new ResponseEntity<>(novaLeitura, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todas as leituras de sensores")
    @GetMapping
    public ResponseEntity<List<LeituraSensorResponseDTO>> listarTodasLeituras() {
        List<LeituraSensorResponseDTO> leituras = leituraSensorService.listarTodasLeituras();
        return ResponseEntity.ok(leituras);
    }

    @Operation(summary = "Busca uma leitura de sensor pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leitura encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LeituraSensorResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Leitura não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LeituraSensorResponseDTO> buscarLeituraPorId(
            @Parameter(description = "ID da leitura a ser buscada", required = true) @PathVariable UUID id) {
        LeituraSensorResponseDTO leitura = leituraSensorService.buscarLeituraPorId(id);
        return ResponseEntity.ok(leitura);
    }

    @Operation(summary = "Lista leituras de um sensor específico",
            description = "Retorna leituras de um sensor. Pode filtrar por período (início e fim) ou pelas últimas N leituras.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leituras retornadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado")
    })
    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<List<LeituraSensorResponseDTO>> listarLeiturasPorSensor(
            @Parameter(description = "ID do sensor para buscar as leituras", required = true) @PathVariable UUID sensorId,
            @Parameter(description = "Data e hora de início do período (formato ISO: yyyy-MM-ddTHH:mm:ss)", example = "2024-05-01T00:00:00")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @Parameter(description = "Data e hora de fim do período (formato ISO: yyyy-MM-ddTHH:mm:ss)", example = "2024-05-01T23:59:59")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            @Parameter(description = "Número das últimas N leituras a serem retornadas", example = "10")
            @RequestParam(required = false) Integer ultimasN) {

        List<LeituraSensorResponseDTO> leituras;
        if (ultimasN != null && ultimasN > 0) {
            leituras = leituraSensorService.listarUltimasNLeiturasPorSensor(sensorId, ultimasN);
        } else if (inicio != null && fim != null) {
            leituras = leituraSensorService.listarLeiturasPorSensorEPeriodo(sensorId, inicio, fim);
        } else {
            leituras = leituraSensorService.listarLeiturasPorSensor(sensorId);
        }
        return ResponseEntity.ok(leituras);
    }

    @Operation(summary = "Deleta uma leitura de sensor pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Leitura deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Leitura não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLeituraSensor(
            @Parameter(description = "ID da leitura a ser deletada", required = true) @PathVariable UUID id) {
        leituraSensorService.deletarLeituraSensor(id);
        return ResponseEntity.noContent().build();
    }
}