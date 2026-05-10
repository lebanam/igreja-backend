package com.igreja360.backend.repository;

import com.igreja360.backend.model.RelatorioCelula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RelatorioCelulaRepository extends JpaRepository<RelatorioCelula, Long> {

    List<RelatorioCelula> findByCelulaIdOrderByDataEncontroDesc(Long celulaId);

    List<RelatorioCelula> findByDataEncontroBetween(LocalDate inicio, LocalDate fim);
}