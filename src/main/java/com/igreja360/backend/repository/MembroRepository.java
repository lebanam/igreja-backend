package com.igreja360.backend.repository;

import com.igreja360.backend.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroRepository extends JpaRepository<Membro, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

}

