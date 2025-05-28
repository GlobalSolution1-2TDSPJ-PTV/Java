package com.global.FloodWatch.repository;

import com.global.FloodWatch.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário.
    // Exemplo: Encontrar um usuário pelo email
    Optional<Usuario> findByEmail(String email);
}
