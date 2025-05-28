package com.global.FloodWatch.repository;

import com.global.FloodWatch.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    // Encontrar sensores por tipo
    List<Sensor> findByTipo(String tipo);

    // Encontrar sensores por status de atividade ('S' ou 'N')
    List<Sensor> findByAtivo(String ativo);

    // Encontrar sensores por localização (pode ser uma busca parcial)
    List<Sensor> findByLocalizacaoContainingIgnoreCase(String localizacao);
}