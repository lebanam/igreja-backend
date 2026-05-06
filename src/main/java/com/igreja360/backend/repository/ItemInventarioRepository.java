package com.igreja360.backend.repository;

import com.igreja360.backend.model.ItemInventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemInventarioRepository extends JpaRepository<ItemInventario, Long> {

    List<ItemInventario> findByCategoriaIdOrderByNomeAsc(Long categoriaId);
}