package com.global.FloodWatch.controller;

import com.global.FloodWatch.dto.SosRequestDTO;
import com.global.FloodWatch.dto.SosResponseDTO;
import com.global.FloodWatch.dto.SosStatusUpdateRequestDTO;
import com.global.FloodWatch.service.SosService;
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
@RequestMapping("/api/sos")
@Tag(name = "Pedidos de SOS", description = "APIs para gerenciamento de pedidos de socorro")
public class SosController {

    private final SosService sosService;

    @Autowired
    public SosController(SosService sosService) {
        this.sosService = sosService;
    }

    @Operation(summary = "Cria um novo pedido de SOS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido de SOS criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SosResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário associado não encontrado")
    })
    @PostMapping
    public ResponseEntity<SosResponseDTO> criarSos(@Valid @RequestBody SosRequestDTO sosRequestDTO) {
        SosResponseDTO novoSos = sosService.criarSos(sosRequestDTO);
        return new ResponseEntity<>(novoSos, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os pedidos de SOS")
    @GetMapping
    public ResponseEntity<List<SosResponseDTO>> listarTodosSos() {
        List<SosResponseDTO> listaSos = sosService.listarTodosSos();
        return ResponseEntity.ok(listaSos);
    }

    @Operation(summary = "Busca um pedido de SOS pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido de SOS encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SosResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Pedido de SOS não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SosResponseDTO> buscarSosPorId(
            @Parameter(description = "ID do pedido de SOS", required = true) @PathVariable UUID id) {
        SosResponseDTO sos = sosService.buscarSosPorId(id);
        return ResponseEntity.ok(sos);
    }

    @Operation(summary = "Lista pedidos de SOS por ID do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de SOS do usuário retornada"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<SosResponseDTO>> listarSosPorUsuario(
            @Parameter(description = "ID do usuário para buscar seus pedidos de SOS", required = true) @PathVariable UUID usuarioId) {
        List<SosResponseDTO> listaSos = sosService.listarSosPorUsuario(usuarioId);
        return ResponseEntity.ok(listaSos);
    }

    @Operation(summary = "Atualiza o status de um pedido de SOS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do SOS atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SosResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Status inválido fornecido"),
            @ApiResponse(responseCode = "404", description = "Pedido de SOS não encontrado")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<SosResponseDTO> atualizarStatusSos(
            @Parameter(description = "ID do pedido de SOS a ser atualizado", required = true) @PathVariable UUID id,
            @Valid @RequestBody SosStatusUpdateRequestDTO statusUpdateRequestDTO) {
        SosResponseDTO sosAtualizado = sosService.atualizarStatusSos(id, statusUpdateRequestDTO);
        return ResponseEntity.ok(sosAtualizado);
    }

    @Operation(summary = "Deleta um pedido de SOS pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido de SOS deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido de SOS não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSos(
            @Parameter(description = "ID do pedido de SOS a ser deletado", required = true) @PathVariable UUID id) {
        sosService.deletarSos(id);
        return ResponseEntity.noContent().build();
    }
}