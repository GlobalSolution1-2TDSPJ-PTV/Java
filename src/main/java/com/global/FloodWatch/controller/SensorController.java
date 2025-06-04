package com.global.FloodWatch.controller;

import com.global.FloodWatch.dto.SensorRequestDTO;
import com.global.FloodWatch.dto.SensorResponseDTO;
import com.global.FloodWatch.service.SensorService;
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
@RequestMapping("/api/sensores")
@Tag(name = "Sensores", description = "APIs para gerenciamento de sensores IoT")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Operation(summary = "Cria um novo sensor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sensor criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SensorResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<SensorResponseDTO> criarSensor(@Valid @RequestBody SensorRequestDTO sensorRequestDTO) {
        SensorResponseDTO novoSensor = sensorService.criarSensor(sensorRequestDTO);
        return new ResponseEntity<>(novoSensor, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os sensores")
    @GetMapping
    public ResponseEntity<List<SensorResponseDTO>> listarTodosSensores() {
        List<SensorResponseDTO> sensores = sensorService.listarTodosSensores();
        return ResponseEntity.ok(sensores);
    }

    @Operation(summary = "Busca um sensor pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensor encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SensorResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> buscarSensorPorId(
            @Parameter(description = "ID do sensor a ser buscado", required = true) @PathVariable UUID id) {
        SensorResponseDTO sensor = sensorService.buscarSensorPorId(id);
        return ResponseEntity.ok(sensor);
    }

    @Operation(summary = "Atualiza um sensor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensor atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SensorResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SensorResponseDTO> atualizarSensor(
            @Parameter(description = "ID do sensor a ser atualizado", required = true) @PathVariable UUID id,
            @Valid @RequestBody SensorRequestDTO sensorRequestDTO) {
        SensorResponseDTO sensorAtualizado = sensorService.atualizarSensor(id, sensorRequestDTO);
        return ResponseEntity.ok(sensorAtualizado);
    }

    @Operation(summary = "Deleta um sensor pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sensor deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSensor(
            @Parameter(description = "ID do sensor a ser deletado", required = true) @PathVariable UUID id) {
        sensorService.deletarSensor(id);
        return ResponseEntity.noContent().build();
    }
}