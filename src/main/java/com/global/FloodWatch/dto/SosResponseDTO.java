package com.global.FloodWatch.dto;

import com.global.FloodWatch.model.Sos;
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
@Schema(description = "DTO para resposta de dados de um pedido de SOS")
public class SosResponseDTO {
    @Schema(description = "ID único do pedido de SOS.")
    private UUID id;
    @Schema(description = "ID do usuário que solicitou SOS.")
    private UUID usuarioId;
    @Schema(description = "Nome do usuário que solicitou SOS.")
    private String nomeUsuario;
    @Schema(description = "Latitude da localização do pedido de SOS.")
    private Double latitude;
    @Schema(description = "Longitude da localização do pedido de SOS.")
    private Double longitude;
    @Schema(description = "Tipo do usuário que solicitou SOS.")
    private Usuario.TipoUsuario tipoUsuario;
    @Schema(description = "Status atual do pedido de SOS.")
    private Sos.StatusSos status;
    @Schema(description = "Data e hora de criação do pedido de SOS.")
    private LocalDateTime criadoEm;
}