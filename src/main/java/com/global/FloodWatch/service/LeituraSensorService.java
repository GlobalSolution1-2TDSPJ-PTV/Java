package com.global.FloodWatch.service;

import com.global.FloodWatch.dto.LeituraSensorRequestDTO;
import com.global.FloodWatch.dto.LeituraSensorResponseDTO;
import com.global.FloodWatch.exception.ResourceNotFoundException;
import com.global.FloodWatch.model.LeituraSensor;
import com.global.FloodWatch.model.Sensor;
import com.global.FloodWatch.repository.LeituraSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.time.LocalDateTime;


@Service
public class LeituraSensorService {

    private final LeituraSensorRepository leituraSensorRepository;
    private final SensorService sensorService; // Para buscar a entidade Sensor

    @Autowired
    public LeituraSensorService(LeituraSensorRepository leituraSensorRepository, SensorService sensorService) {
        this.leituraSensorRepository = leituraSensorRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public LeituraSensorResponseDTO criarLeituraSensor(LeituraSensorRequestDTO leituraRequestDTO) {
        Sensor sensor = sensorService.buscarEntidadeSensorPorId(leituraRequestDTO.getSensorId());

        LeituraSensor leitura = new LeituraSensor();
        leitura.setSensor(sensor);
        leitura.setValor(leituraRequestDTO.getValor());
        leitura.setUnidade(leituraRequestDTO.getUnidade());
        // O @PrePersist na entidade LeituraSensor cuidará do ID e lidoEm

        LeituraSensor novaLeitura = leituraSensorRepository.save(leitura);
        return mapToLeituraSensorResponseDTO(novaLeitura);
    }

    @Transactional(readOnly = true)
    public List<LeituraSensorResponseDTO> listarTodasLeituras() {
        return leituraSensorRepository.findAll().stream()
                .map(this::mapToLeituraSensorResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LeituraSensorResponseDTO buscarLeituraPorId(UUID id) {
        LeituraSensor leitura = leituraSensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leitura de sensor não encontrada com o ID: " + id));
        return mapToLeituraSensorResponseDTO(leitura);
    }

    @Transactional(readOnly = true)
    public List<LeituraSensorResponseDTO> listarLeiturasPorSensor(UUID sensorId) {
        Sensor sensor = sensorService.buscarEntidadeSensorPorId(sensorId);
        return leituraSensorRepository.findBySensor(sensor).stream()
                .map(this::mapToLeituraSensorResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LeituraSensorResponseDTO> listarLeiturasPorSensorEPeriodo(UUID sensorId, LocalDateTime inicio, LocalDateTime fim) {
        Sensor sensor = sensorService.buscarEntidadeSensorPorId(sensorId);
        return leituraSensorRepository.findBySensorAndLidoEmBetween(sensor, inicio, fim).stream()
                .map(this::mapToLeituraSensorResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LeituraSensorResponseDTO> listarUltimasNLeiturasPorSensor(UUID sensorId, int n) {
        Sensor sensor = sensorService.buscarEntidadeSensorPorId(sensorId);
        Pageable topN = PageRequest.of(0, n);
        return leituraSensorRepository.findTopBySensorOrderByLidoEmDesc(sensor, topN).stream()
                .map(this::mapToLeituraSensorResponseDTO)
                .collect(Collectors.toList());
    }



    @Transactional
    public void deletarLeituraSensor(UUID id) {
        if (!leituraSensorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Leitura de sensor não encontrada com o ID: " + id);
        }
        leituraSensorRepository.deleteById(id);
    }

    private LeituraSensorResponseDTO mapToLeituraSensorResponseDTO(LeituraSensor leitura) {
        return new LeituraSensorResponseDTO(
                leitura.getId(),
                leitura.getSensor().getId(),
                leitura.getValor(),
                leitura.getUnidade(),
                leitura.getLidoEm()
        );
    }
}
