package com.igreja360.backend.repository;

import com.igreja360.backend.model.CategoriaInventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaInventarioRepository extends JpaRepository<CategoriaInventario, Long> {

    List<CategoriaInventario> findAllByOrderByNomeAsc();
}