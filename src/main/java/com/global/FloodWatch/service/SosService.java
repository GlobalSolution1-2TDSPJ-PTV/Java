package com.global.FloodWatch.service;

import com.global.FloodWatch.dto.SosRequestDTO;
import com.global.FloodWatch.dto.SosResponseDTO;
import com.global.FloodWatch.dto.SosStatusUpdateRequestDTO;
import com.global.FloodWatch.exception.ResourceNotFoundException;
import com.global.FloodWatch.model.Sos;
import com.global.FloodWatch.model.Usuario;
import com.global.FloodWatch.repository.SosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SosService {

    private final SosRepository sosRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public SosService(SosRepository sosRepository, UsuarioService usuarioService) {
        this.sosRepository = sosRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public SosResponseDTO criarSos(SosRequestDTO sosRequestDTO) {
        Usuario usuario = usuarioService.buscarEntidadeUsuarioPorId(sosRequestDTO.getUsuarioId());

        Sos sos = new Sos();
        sos.setUsuario(usuario);
        sos.setLatitude(sosRequestDTO.getLatitude());
        sos.setLongitude(sosRequestDTO.getLongitude());
        // O @PrePersist na entidade Sos cuidará do ID, criadoEm e status inicial

        Sos novoSos = sosRepository.save(sos);
        return mapToSosResponseDTO(novoSos);
    }

    @Transactional(readOnly = true)
    public List<SosResponseDTO> listarTodosSos() {
        return sosRepository.findAll().stream()
                .map(this::mapToSosResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SosResponseDTO buscarSosPorId(UUID id) {
        Sos sos = sosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SOS não encontrado com o ID: " + id));
        return mapToSosResponseDTO(sos);
    }

    @Transactional
    public SosResponseDTO atualizarStatusSos(UUID id, SosStatusUpdateRequestDTO statusUpdateRequestDTO) {
        Sos sos = sosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SOS não encontrado com o ID: " + id));
        sos.setStatus(statusUpdateRequestDTO.getStatus());
        Sos sosAtualizado = sosRepository.save(sos);
        return mapToSosResponseDTO(sosAtualizado);
    }

    @Transactional
    public void deletarSos(UUID id) {
        if (!sosRepository.existsById(id)) {
            throw new ResourceNotFoundException("SOS não encontrado com o ID: " + id);
        }
        sosRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<SosResponseDTO> listarSosPorUsuario(UUID usuarioId) {
        Usuario usuario = usuarioService.buscarEntidadeUsuarioPorId(usuarioId);
        return sosRepository.findByUsuario(usuario).stream()
                .map(this::mapToSosResponseDTO)
                .collect(Collectors.toList());
    }


    private SosResponseDTO mapToSosResponseDTO(Sos sos) {
        return new SosResponseDTO(
                sos.getId(),
                sos.getUsuario().getId(),
                sos.getUsuario().getNome(),
                sos.getLatitude(),
                sos.getLongitude(),
                sos.getUsuario().getTipoUsuario(),
                sos.getStatus(),
                sos.getCriadoEm()
        );
    }
}
