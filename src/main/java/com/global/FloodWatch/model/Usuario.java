package com.global.FloodWatch.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(columnDefinition = "RAW(16)")
    private UUID id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    private String telefone;

    private Double latitude;
    private Double longitude;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
        criadoEm = LocalDateTime.now();
    }

    public enum TipoUsuario {
        comum, defesa_civil, ong
    }
}