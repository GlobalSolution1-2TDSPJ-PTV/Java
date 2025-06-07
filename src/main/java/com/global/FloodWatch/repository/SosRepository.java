package com.global.FloodWatch.repository;

import com.global.FloodWatch.model.Sos;
import com.global.FloodWatch.model.StatusSos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;
import com.global.FloodWatch.model.Usuario;


@Repository
public interface SosRepository extends JpaRepository<Sos, UUID> {
    // Encontrar todos os SOS por usuário
    List<Sos> findByUsuario(Usuario usuario);

    // Encontrar todos os SOS por status
    List<Sos> findByStatus(StatusSos status);

    // Encontrar todos os SOS por usuário e status
    List<Sos> findByUsuarioAndStatus(Usuario usuario, StatusSos status);
}