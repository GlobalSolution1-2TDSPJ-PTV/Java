package com.global.FloodWatch.controller;

import com.global.FloodWatch.dto.UsuarioRequestDTO;
import com.global.FloodWatch.dto.UsuarioResponseDTO;
import com.global.FloodWatch.service.UsuarioService;
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
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "APIs para gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Cria um novo usuário", description = "Registra um novo usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO novoUsuario = usuarioService.criarUsuario(usuarioRequestDTO);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDTO.class)) }) // Correto, mas pode ser um array
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Busca um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(
            @Parameter(description = "ID do usuário a ser buscado", required = true, example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") @PathVariable UUID id) {
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Atualiza um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @Parameter(description = "ID do usuário a ser atualizado", required = true) @PathVariable UUID id,
            @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioRequestDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @Operation(summary = "Deleta um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(
            @Parameter(description = "ID do usuário a ser deletado", required = true) @PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}