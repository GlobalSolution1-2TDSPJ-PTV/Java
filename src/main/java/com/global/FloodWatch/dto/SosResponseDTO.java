package com.global.FloodWatch.dto;

import com.global.FloodWatch.model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SosResponseDTO {
    private UUID id;
    private UUID usuarioId;
    private String nomeUsuario;
    private Double latitude;
    private Double longitude;
    private Usuario.TipoUsuario tipoUsuario;
    private com.global.FloodWatch.model.Sos.StatusSos status;
    private LocalDateTime criadoEm;
}
