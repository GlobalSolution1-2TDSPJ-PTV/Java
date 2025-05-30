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
public class UsuarioResponseDTO {
    private UUID id;
    private String nome;
    private String email;
    private Usuario.TipoUsuario tipoUsuario;
    private String telefone;
    private Double latitude;
    private Double longitude;
    private LocalDateTime criadoEm;
}
