package com.global.FloodWatch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(tipoUsuario == TipoUsuario.DEFESA_CIVIL) {
            return List.of(new SimpleGrantedAuthority("ROLE_DEFESA_CIVIL"), new SimpleGrantedAuthority("ROLE_COMUM"), new SimpleGrantedAuthority("ROLE_ONG"));
        } else if(tipoUsuario == TipoUsuario.ONG) {
            return List.of(new SimpleGrantedAuthority("ROLE_ONG"), new SimpleGrantedAuthority("ROLE_COMUM"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_COMUM"));
    }

    @Override
    public String getPassword() {
        return senhaHash;
    }

    @Override
    public String getUsername() {
        return email;
    }
}