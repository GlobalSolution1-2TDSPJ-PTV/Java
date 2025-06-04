package com.global.FloodWatch.controller;

import com.global.FloodWatch.dto.AlertaRequestDTO;
import com.global.FloodWatch.dto.AlertaResponseDTO;
import com.global.FloodWatch.dto.AlertaResolvidoUpdateRequestDTO;
import com.global.FloodWatch.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alertas")
@Tag(name = "Alertas", description = "APIs para gerenciamento de alertas de enchente")
public class AlertaController {

    private final AlertaService alertaService;

    @Autowired
    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @Operation(summary = "Cria um novo alerta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alerta criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlertaResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Sensor associado não encontrado")
    })
    @PostMapping
    public ResponseEntity<AlertaResponseDTO> criarAlerta(@Valid @RequestBody AlertaRequestDTO alertaRequestDTO) {
        AlertaResponseDTO novoAlerta = alertaService.criarAlerta(alertaRequestDTO);
        return new ResponseEntity<>(novoAlerta, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os alertas")
    @GetMapping
    public ResponseEntity<List<AlertaResponseDTO>> listarTodosAlertas() {
        List<AlertaResponseDTO> alertas = alertaService.listarTodosAlertas();
        return ResponseEntity.ok(alertas);
    }

    @Operation(summary = "Busca um alerta pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alerta encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlertaResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Alerta não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponseDTO> buscarAlertaPorId(
            @Parameter(description = "ID do alerta a ser buscado", required = true) @PathVariable UUID id) {
        AlertaResponseDTO alerta = alertaService.buscarAlertaPorId(id);
        return ResponseEntity.ok(alerta);
    }

    @Operation(summary = "Lista alertas por ID do sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alertas do sensor retornados"),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado")
    })
    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<List<AlertaResponseDTO>> listarAlertasPorSensor(
            @Parameter(description = "ID do sensor para buscar seus alertas", required = true) @PathVariable UUID sensorId) {
        List<AlertaResponseDTO> alertas = alertaService.listarAlertasPorSensor(sensorId);
        return ResponseEntity.ok(alertas);
    }

    @Operation(summary = "Atualiza o status de resolução de um alerta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do alerta atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlertaResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Status de resolução inválido"),
            @ApiResponse(responseCode = "404", description = "Alerta não encontrado")
    })
    @PatchMapping("/{id}/resolver")
    public ResponseEntity<AlertaResponseDTO> atualizarStatusResolvidoAlerta(
            @Parameter(description = "ID do alerta a ser atualizado", required = true) @PathVariable UUID id,
            @Valid @RequestBody AlertaResolvidoUpdateRequestDTO resolvidoUpdateRequestDTO) {
        AlertaResponseDTO alertaAtualizado = alertaService.atualizarStatusResolvidoAlerta(id, resolvidoUpdateRequestDTO);
        return ResponseEntity.ok(alertaAtualizado);
    }

    @Operation(summary = "Deleta um alerta pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alerta deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Alerta não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAlerta(
            @Parameter(description = "ID do alerta a ser deletado", required = true) @PathVariable UUID id) {
        alertaService.deletarAlerta(id);
        return ResponseEntity.noContent().build();
    }
}