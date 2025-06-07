package com.global.FloodWatch.controller;

import com.global.FloodWatch.dto.AbrigoRequestDTO;
import com.global.FloodWatch.dto.AbrigoResponseDTO;
import com.global.FloodWatch.service.AbrigoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/abrigos")
@Validated
@Tag(name = "Abrigos", description = "APIs para gerenciamento de abrigos temporários")
public class AbrigoController {

    private final AbrigoService abrigoService;

    @Autowired
    public AbrigoController(AbrigoService abrigoService) {
        this.abrigoService = abrigoService;
    }

    @Operation(summary = "Cria um novo abrigo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Abrigo criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AbrigoResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<AbrigoResponseDTO> criarAbrigo(@Valid @RequestBody AbrigoRequestDTO abrigoRequestDTO) {
        AbrigoResponseDTO novoAbrigo = abrigoService.criarAbrigo(abrigoRequestDTO);
        return new ResponseEntity<>(novoAbrigo, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os abrigos")
    @GetMapping
    public ResponseEntity<List<AbrigoResponseDTO>> listarTodosAbrigos() {
        List<AbrigoResponseDTO> abrigos = abrigoService.listarTodosAbrigos();
        return ResponseEntity.ok(abrigos);
    }


    @Operation(summary = "Lista todos os abrigos com paginação e ordenação")
    @GetMapping("/paginated")
    public ResponseEntity<Page<AbrigoResponseDTO>> listarTodosAbrigos(
            @Parameter(description = "Informações de paginação e ordenação (ex: page=0&size=10&sort=nome,asc)")
            Pageable pageable) {

        Page<AbrigoResponseDTO> abrigos = abrigoService.listarTodosAbrigos(pageable);
        return ResponseEntity.ok(abrigos);
    }


    @Operation(summary = "Busca um abrigo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigo encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AbrigoResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Abrigo não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AbrigoResponseDTO> buscarAbrigoPorId(
            @Parameter(description = "ID do abrigo a ser buscado", required = true) @PathVariable UUID id) {
        AbrigoResponseDTO abrigo = abrigoService.buscarAbrigoPorId(id);
        return ResponseEntity.ok(abrigo);
    }

    @Operation(summary = "Atualiza um abrigo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abrigo atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AbrigoResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Abrigo não encontrado")
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<AbrigoResponseDTO> atualizarAbrigo(
            @Parameter(description = "ID do abrigo a ser atualizado", required = true) @PathVariable UUID id,
            @Valid @RequestBody AbrigoRequestDTO abrigoRequestDTO) {
        AbrigoResponseDTO abrigoAtualizado = abrigoService.atualizarAbrigo(id, abrigoRequestDTO);
        return ResponseEntity.ok(abrigoAtualizado);
    }

    @Operation(summary = "Atualiza a ocupação atual de um abrigo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ocupação do abrigo atualizada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AbrigoResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Valor de ocupação inválido"),
            @ApiResponse(responseCode = "404", description = "Abrigo não encontrado")
    })
    @PatchMapping("/{id}/ocupacao")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<AbrigoResponseDTO> atualizarOcupacaoAbrigo(
            @Parameter(description = "ID do abrigo para atualizar a ocupação", required = true) @PathVariable UUID id,
            @Parameter(description = "Novo número de ocupantes", required = true, example = "80")
            @RequestParam @Min(value = 0, message = "Ocupação não pode ser negativa") Integer novaOcupacao) {
        AbrigoResponseDTO abrigoAtualizado = abrigoService.atualizarOcupacaoAbrigo(id, novaOcupacao);
        return ResponseEntity.ok(abrigoAtualizado);
    }

    @Operation(summary = "Deleta um abrigo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Abrigo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Abrigo não encontrado")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> deletarAbrigo(
            @Parameter(description = "ID do abrigo a ser deletado", required = true) @PathVariable UUID id) {
        abrigoService.deletarAbrigo(id);
        return ResponseEntity.noContent().build();
    }
}