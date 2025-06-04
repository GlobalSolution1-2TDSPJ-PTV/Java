package com.global.FloodWatch.controller;

import com.global.FloodWatch.dto.DroneRequestDTO;
import com.global.FloodWatch.dto.DroneResponseDTO;
import com.global.FloodWatch.service.DroneService;
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
@RequestMapping("/api/drones")
@Tag(name = "Drones", description = "APIs para gerenciamento de drones autônomos")
public class DroneController {

    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @Operation(summary = "Cria um novo drone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Drone criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<DroneResponseDTO> criarDrone(@Valid @RequestBody DroneRequestDTO droneRequestDTO) {
        DroneResponseDTO novoDrone = droneService.criarDrone(droneRequestDTO);
        return new ResponseEntity<>(novoDrone, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os drones")
    @GetMapping
    public ResponseEntity<List<DroneResponseDTO>> listarTodosDrones() {
        List<DroneResponseDTO> drones = droneService.listarTodosDrones();
        return ResponseEntity.ok(drones);
    }

    @Operation(summary = "Busca um drone pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Drone não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DroneResponseDTO> buscarDronePorId(
            @Parameter(description = "ID do drone a ser buscado", required = true) @PathVariable UUID id) {
        DroneResponseDTO drone = droneService.buscarDronePorId(id);
        return ResponseEntity.ok(drone);
    }

    @Operation(summary = "Atualiza um drone existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Drone não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DroneResponseDTO> atualizarDrone(
            @Parameter(description = "ID do drone a ser atualizado", required = true) @PathVariable UUID id,
            @Valid @RequestBody DroneRequestDTO droneRequestDTO) {
        DroneResponseDTO droneAtualizado = droneService.atualizarDrone(id, droneRequestDTO);
        return ResponseEntity.ok(droneAtualizado);
    }

    @Operation(summary = "Deleta um drone pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Drone deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Drone não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDrone(
            @Parameter(description = "ID do drone a ser deletado", required = true) @PathVariable UUID id) {
        droneService.deletarDrone(id);
        return ResponseEntity.noContent().build();
    }
}