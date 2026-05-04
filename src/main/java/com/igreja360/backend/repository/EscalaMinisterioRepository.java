package com.igreja360.backend.repository;

import com.igreja360.backend.model.EscalaMinisterio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EscalaMinisterioRepository extends JpaRepository<EscalaMinisterio, Long> {

    List<EscalaMinisterio> findByMinisterioIdOrderByDataAscHorarioAsc(Long ministerioId);
}