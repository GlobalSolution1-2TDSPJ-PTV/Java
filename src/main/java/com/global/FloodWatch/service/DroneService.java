package com.global.FloodWatch.service;

import com.global.FloodWatch.dto.DroneRequestDTO;
import com.global.FloodWatch.dto.DroneResponseDTO;
import com.global.FloodWatch.exception.ResourceNotFoundException;
import com.global.FloodWatch.model.Drone;
import com.global.FloodWatch.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DroneService {

    private final DroneRepository droneRepository;

    @Autowired
    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Transactional
    public DroneResponseDTO criarDrone(DroneRequestDTO droneRequestDTO) {
        Drone drone = new Drone();
        drone.setNome(droneRequestDTO.getNome());
        drone.setStatus(droneRequestDTO.getStatus());
        drone.setLocalAtual(droneRequestDTO.getLocalAtual());
        drone.setLatitude(droneRequestDTO.getLatitude());
        drone.setLongitude(droneRequestDTO.getLongitude());
        // O @PrePersist na entidade Drone cuidará do ID e status inicial se não for passado

        Drone novoDrone = droneRepository.save(drone);
        return mapToDroneResponseDTO(novoDrone);
    }

    @Transactional(readOnly = true)
    public List<DroneResponseDTO> listarTodosDrones() {
        return droneRepository.findAll().stream()
                .map(this::mapToDroneResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DroneResponseDTO buscarDronePorId(UUID id) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drone não encontrado com o ID: " + id));
        return mapToDroneResponseDTO(drone);
    }

    @Transactional
    public DroneResponseDTO atualizarDrone(UUID id, DroneRequestDTO droneRequestDTO) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drone não encontrado com o ID: " + id));

        drone.setNome(droneRequestDTO.getNome());
        drone.setStatus(droneRequestDTO.getStatus());
        drone.setLocalAtual(droneRequestDTO.getLocalAtual());
        drone.setLatitude(droneRequestDTO.getLatitude());
        drone.setLongitude(droneRequestDTO.getLongitude());

        Drone droneAtualizado = droneRepository.save(drone);
        return mapToDroneResponseDTO(droneAtualizado);
    }

    @Transactional
    public void deletarDrone(UUID id) {
        if (!droneRepository.existsById(id)) {
            throw new ResourceNotFoundException("Drone não encontrado com o ID: " + id);
        }
        droneRepository.deleteById(id);
    }

    private DroneResponseDTO mapToDroneResponseDTO(Drone drone) {
        return new DroneResponseDTO(
                drone.getId(),
                drone.getNome(),
                drone.getStatus(),
                drone.getLocalAtual(),
                drone.getLatitude(),
                drone.getLongitude()
        );
    }
}
