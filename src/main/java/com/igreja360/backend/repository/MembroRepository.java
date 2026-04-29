package com.igreja360.backend.repository;

import com.igreja360.backend.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembroRepository extends JpaRepository<Membro, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    List<Membro> findByGc(String gc); // ✅ ESSA LINHA É A CHAVE
}