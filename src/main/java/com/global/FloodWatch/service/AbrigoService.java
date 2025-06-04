package com.global.FloodWatch.service;

import com.global.FloodWatch.dto.AbrigoRequestDTO;
import com.global.FloodWatch.dto.AbrigoResponseDTO;
import com.global.FloodWatch.exception.ResourceNotFoundException;
import com.global.FloodWatch.model.Abrigo;
import com.global.FloodWatch.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AbrigoService {

    private final AbrigoRepository abrigoRepository;

    @Autowired
    public AbrigoService(AbrigoRepository abrigoRepository) {
        this.abrigoRepository = abrigoRepository;
    }

    @Transactional
    public AbrigoResponseDTO criarAbrigo(AbrigoRequestDTO abrigoRequestDTO) {
        Abrigo abrigo = new Abrigo();
        abrigo.setNome(abrigoRequestDTO.getNome());
        abrigo.setCapacidade(abrigoRequestDTO.getCapacidade());
        if (abrigoRequestDTO.getOcupacaoAtual() != null) {
            abrigo.setOcupacaoAtual(abrigoRequestDTO.getOcupacaoAtual());
        }
        abrigo.setLatitude(abrigoRequestDTO.getLatitude());
        abrigo.setLongitude(abrigoRequestDTO.getLongitude());
        abrigo.setResponsavel(abrigoRequestDTO.getResponsavel());


        Abrigo novoAbrigo = abrigoRepository.save(abrigo);
        return mapToAbrigoResponseDTO(novoAbrigo);
    }

    @Transactional(readOnly = true)
    public List<AbrigoResponseDTO> listarTodosAbrigos() {
        return abrigoRepository.findAll().stream()
                .map(this::mapToAbrigoResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AbrigoResponseDTO buscarAbrigoPorId(UUID id) {
        Abrigo abrigo = abrigoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Abrigo não encontrado com o ID: " + id));
        return mapToAbrigoResponseDTO(abrigo);
    }

    @Transactional
    public AbrigoResponseDTO atualizarAbrigo(UUID id, AbrigoRequestDTO abrigoRequestDTO) {
        Abrigo abrigo = abrigoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Abrigo não encontrado com o ID: " + id));

        abrigo.setNome(abrigoRequestDTO.getNome());
        abrigo.setCapacidade(abrigoRequestDTO.getCapacidade());
        if (abrigoRequestDTO.getOcupacaoAtual() != null) {
            abrigo.setOcupacaoAtual(abrigoRequestDTO.getOcupacaoAtual());
        }
        abrigo.setLatitude(abrigoRequestDTO.getLatitude());
        abrigo.setLongitude(abrigoRequestDTO.getLongitude());
        abrigo.setResponsavel(abrigoRequestDTO.getResponsavel());

        Abrigo abrigoAtualizado = abrigoRepository.save(abrigo);
        return mapToAbrigoResponseDTO(abrigoAtualizado);
    }

    @Transactional
    public AbrigoResponseDTO atualizarOcupacaoAbrigo(UUID id, Integer novaOcupacao) {
        Abrigo abrigo = abrigoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Abrigo não encontrado com o ID: " + id));
        if (novaOcupacao < 0 || (abrigo.getCapacidade() != null && novaOcupacao > abrigo.getCapacidade())) {
            throw new IllegalArgumentException("Ocupação inválida. Deve ser entre 0 e a capacidade do abrigo.");
        }
        abrigo.setOcupacaoAtual(novaOcupacao);
        Abrigo abrigoAtualizado = abrigoRepository.save(abrigo);
        return mapToAbrigoResponseDTO(abrigoAtualizado);
    }


    @Transactional
    public void deletarAbrigo(UUID id) {
        if (!abrigoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Abrigo não encontrado com o ID: " + id);
        }
        abrigoRepository.deleteById(id);
    }

    private AbrigoResponseDTO mapToAbrigoResponseDTO(Abrigo abrigo) {
        return new AbrigoResponseDTO(
                abrigo.getId(),
                abrigo.getNome(),
                abrigo.getCapacidade(),
                abrigo.getOcupacaoAtual(),
                abrigo.getLatitude(),
                abrigo.getLongitude(),
                abrigo.getResponsavel()
        );
    }
}

