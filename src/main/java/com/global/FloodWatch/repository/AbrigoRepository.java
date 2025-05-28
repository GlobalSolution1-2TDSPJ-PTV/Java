package com.global.FloodWatch.repository;

import com.global.FloodWatch.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface AbrigoRepository extends JpaRepository<Abrigo, UUID> {
    // Encontrar abrigo pelo nome (ignorando maiúsculas/minúsculas)
    Optional<Abrigo> findByNomeIgnoreCase(String nome);

    // Encontrar abrigos com capacidade maior ou igual a um valor
    List<Abrigo> findByCapacidadeGreaterThanEqual(Integer capacidade);

    // Encontrar abrigos onde a ocupação atual é menor que a capacidade (há vagas)
    @Query("SELECT a FROM Abrigo a WHERE a.ocupacaoAtual < a.capacidade")
    List<Abrigo> findAbrigosComVagas();

    // Encontrar abrigos por responsável
    List<Abrigo> findByResponsavelContainingIgnoreCase(String responsavel);
}
