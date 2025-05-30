package com.global.FloodWatch.service;

import com.global.FloodWatch.dto.AlertaRequestDTO;
import com.global.FloodWatch.dto.AlertaResponseDTO;
import com.global.FloodWatch.dto.AlertaResolvidoUpdateRequestDTO;
import com.global.FloodWatch.exception.ResourceNotFoundException;
import com.global.FloodWatch.model.Alerta;
import com.global.FloodWatch.model.Sensor;
import com.global.FloodWatch.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final SensorService sensorService;

    @Autowired
    public AlertaService(AlertaRepository alertaRepository, SensorService sensorService) {
        this.alertaRepository = alertaRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public AlertaResponseDTO criarAlerta(AlertaRequestDTO alertaRequestDTO) {
        Sensor sensor = sensorService.buscarEntidadeSensorPorId(alertaRequestDTO.getSensorId());

        Alerta alerta = new Alerta();
        alerta.setSensor(sensor);
        alerta.setTipo(alertaRequestDTO.getTipo());
        alerta.setMensagem(alertaRequestDTO.getMensagem());
        alerta.setNivel(alertaRequestDTO.getNivel());
        // O @PrePersist na entidade Alerta cuidará do ID, criadoEm e resolvido inicial

        Alerta novoAlerta = alertaRepository.save(alerta);
        return mapToAlertaResponseDTO(novoAlerta);
    }

    @Transactional(readOnly = true)
    public List<AlertaResponseDTO> listarTodosAlertas() {
        return alertaRepository.findAll().stream()
                .map(this::mapToAlertaResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlertaResponseDTO buscarAlertaPorId(UUID id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com o ID: " + id));
        return mapToAlertaResponseDTO(alerta);
    }

    @Transactional(readOnly = true)
    public List<AlertaResponseDTO> listarAlertasPorSensor(UUID sensorId) {
        Sensor sensor = sensorService.buscarEntidadeSensorPorId(sensorId);
        return alertaRepository.findBySensor(sensor).stream()
                .map(this::mapToAlertaResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AlertaResponseDTO atualizarStatusResolvidoAlerta(UUID id, AlertaResolvidoUpdateRequestDTO resolvidoUpdateRequestDTO) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com o ID: " + id));
        alerta.setResolvido(resolvidoUpdateRequestDTO.getResolvido());
        Alerta alertaAtualizado = alertaRepository.save(alerta);
        return mapToAlertaResponseDTO(alertaAtualizado);
    }


    @Transactional
    public void deletarAlerta(UUID id) {
        if (!alertaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Alerta não encontrado com o ID: " + id);
        }
        alertaRepository.deleteById(id);
    }

    private AlertaResponseDTO mapToAlertaResponseDTO(Alerta alerta) {
        return new AlertaResponseDTO(
                alerta.getId(),
                alerta.getSensor().getId(),
                alerta.getTipo(),
                alerta.getMensagem(),
                alerta.getNivel(),
                alerta.getCriadoEm(),
                alerta.getResolvido()
        );
    }
}
