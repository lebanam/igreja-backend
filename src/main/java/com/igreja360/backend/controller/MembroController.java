package com.igreja360.backend.controller;

import com.igreja360.backend.model.Celula;
import com.igreja360.backend.model.Membro;
import com.igreja360.backend.repository.MembroRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membros")
@CrossOrigin(origins = "*")
public class MembroController {

    // 👉 AQUI
    @Autowired
    private MembroRepository repository;

    // LISTAR
    @GetMapping
    public List<Membro> listar() {
        return repository.findAll();
    }

    // SALVAR
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Membro membro) {

        if (repository.existsByCpf(membro.getCpf())) {
            return ResponseEntity.badRequest().body("CPF já cadastrado");
        }

        if (repository.existsByEmail(membro.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        return ResponseEntity.ok(repository.save(membro));
    }

    @PutMapping("/{id}")
    public Membro atualizar(@PathVariable Long id, @RequestBody Membro membroAtualizado) {
        Membro membro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado"));

        membro.setNome(membroAtualizado.getNome());
        membro.setEmail(membroAtualizado.getEmail());
        membro.setCpf(membroAtualizado.getCpf());

        return repository.save(membro);
    }
    // DELETAR
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @ManyToOne
    @JoinColumn(name = "celula_id")
    private Celula celula;
}