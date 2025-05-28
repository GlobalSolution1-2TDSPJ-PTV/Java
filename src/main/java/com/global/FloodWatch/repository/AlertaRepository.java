package com.global.FloodWatch.repository;

import com.global.FloodWatch.model.Alerta;
import com.global.FloodWatch.model.Sensor;
import com.global.FloodWatch.model.Alerta.NivelAlerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, UUID> {
    // Encontrar alertas por sensor
    List<Alerta> findBySensor(Sensor sensor);

    // Encontrar alertas por nível
    List<Alerta> findByNivel(NivelAlerta nivel);

    // Encontrar alertas por status de resolução ('S' ou 'N')
    List<Alerta> findByResolvido(String resolvido);

    // Encontrar alertas por sensor e nível
    List<Alerta> findBySensorAndNivel(Sensor sensor, NivelAlerta nivel);

    // Encontrar alertas por sensor e status de resolução
    List<Alerta> findBySensorAndResolvido(Sensor sensor, String resolvido);
}