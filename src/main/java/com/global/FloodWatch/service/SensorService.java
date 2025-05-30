package com.global.FloodWatch.service;

import com.global.FloodWatch.dto.SensorRequestDTO;
import com.global.FloodWatch.dto.SensorResponseDTO;
import com.global.FloodWatch.exception.ResourceNotFoundException;
import com.global.FloodWatch.model.Sensor;
import com.global.FloodWatch.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public SensorResponseDTO criarSensor(SensorRequestDTO sensorRequestDTO) {
        Sensor sensor = new Sensor();
        sensor.setLocalizacao(sensorRequestDTO.getLocalizacao());
        sensor.setLatitude(sensorRequestDTO.getLatitude());
        sensor.setLongitude(sensorRequestDTO.getLongitude());
        sensor.setTipo(sensorRequestDTO.getTipo());
        sensor.setAtivo(sensorRequestDTO.getAtivo());
        // O @PrePersist na entidade Sensor cuidará do ID e instaladoEm

        Sensor novoSensor = sensorRepository.save(sensor);
        return mapToSensorResponseDTO(novoSensor);
    }

    @Transactional(readOnly = true)
    public List<SensorResponseDTO> listarTodosSensores() {
        return sensorRepository.findAll().stream()
                .map(this::mapToSensorResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SensorResponseDTO buscarSensorPorId(UUID id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com o ID: " + id));
        return mapToSensorResponseDTO(sensor);
    }

    @Transactional(readOnly = true)
    public Sensor buscarEntidadeSensorPorId(UUID id) { // Método para buscar a entidade crua
        return sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com o ID: " + id));
    }

    @Transactional
    public SensorResponseDTO atualizarSensor(UUID id, SensorRequestDTO sensorRequestDTO) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com o ID: " + id));

        sensor.setLocalizacao(sensorRequestDTO.getLocalizacao());
        sensor.setLatitude(sensorRequestDTO.getLatitude());
        sensor.setLongitude(sensorRequestDTO.getLongitude());
        sensor.setTipo(sensorRequestDTO.getTipo());
        sensor.setAtivo(sensorRequestDTO.getAtivo());

        Sensor sensorAtualizado = sensorRepository.save(sensor);
        return mapToSensorResponseDTO(sensorAtualizado);
    }

    @Transactional
    public void deletarSensor(UUID id) {
        if (!sensorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sensor não encontrado com o ID: " + id);
        }
        // Adicionar lógica para verificar dependências (ex: leituras, alertas) antes de excluir
        sensorRepository.deleteById(id);
    }

    private SensorResponseDTO mapToSensorResponseDTO(Sensor sensor) {
        return new SensorResponseDTO(
                sensor.getId(),
                sensor.getLocalizacao(),
                sensor.getLatitude(),
                sensor.getLongitude(),
                sensor.getTipo(),
                sensor.getAtivo(),
                sensor.getInstaladoEm()
        );
    }
}
