package com.global.FloodWatch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
        if (this.id == null) { // Garante que o ID só seja gerado se não estiver definido (útil para updates)
            this.id = UUID.randomUUID();
        }
        this.criadoEm = LocalDateTime.now();
    }

    public enum TipoUsuario {
        comum, defesa_civil, ong
    }
}