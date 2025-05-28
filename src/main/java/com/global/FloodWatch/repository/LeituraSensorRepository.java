package com.global.FloodWatch.repository;

import com.global.FloodWatch.model.LeituraSensor;
import com.global.FloodWatch.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, UUID> {
    // Encontrar todas as leituras de um sensor específico
    List<LeituraSensor> findBySensor(Sensor sensor);

    // Encontrar leituras de um sensor dentro de um período
    List<LeituraSensor> findBySensorAndLidoEmBetween(Sensor sensor, LocalDateTime inicio, LocalDateTime fim);

    // Encontrar as N leituras mais recentes de um sensor
    List<LeituraSensor> findTopBySensorOrderByLidoEmDesc(Sensor sensor, org.springframework.data.domain.Pageable pageable);
    // Exemplo de uso para o método acima:
    // Pageable topN = PageRequest.of(0, N); // Onde N é o número de leituras desejadas
    // leituraSensorRepository.findTopBySensorOrderByLidoEmDesc(sensor, topN);
}