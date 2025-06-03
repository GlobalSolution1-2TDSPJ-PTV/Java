package com.global.FloodWatch.dto;

import com.global.FloodWatch.model.TipoUsuario;
import com.global.FloodWatch.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para resposta de dados de Usuário")
public class UsuarioResponseDTO {
    @Schema(description = "ID único do usuário.")
    private UUID id;
    @Schema(description = "Nome completo do usuário.")
    private String nome;
    @Schema(description = "Endereço de e-mail do usuário.")
    private String email;
    @Schema(description = "Tipo de usuário.")
    private TipoUsuario tipoUsuario;
    @Schema(description = "Número de telefone do usuário.")
    private String telefone;
    @Schema(description = "Latitude da localização do usuário.")
    private Double latitude;
    @Schema(description = "Longitude da localização do usuário.")
    private Double longitude;
    @Schema(description = "Data e hora de criação do registro do usuário.")
    private LocalDateTime criadoEm;
}
