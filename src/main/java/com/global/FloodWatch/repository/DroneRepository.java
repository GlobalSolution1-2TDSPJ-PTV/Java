package com.global.FloodWatch.repository;

import com.global.FloodWatch.model.Drone;
import com.global.FloodWatch.model.Drone.StatusDrone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, UUID> {
    // Encontrar drones por status
    List<Drone> findByStatus(StatusDrone status);

    // Encontrar drone pelo nome (ignorando maiúsculas/minúsculas)
    Optional<Drone> findByNomeIgnoreCase(String nome);
}