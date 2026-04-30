package com.igreja360.backend.controller;
import com.igreja360.backend.dto.MembroResponse;
import com.igreja360.backend.dto.MembroRequest;
import com.igreja360.backend.model.Celula;
import com.igreja360.backend.model.Membro;
import com.igreja360.backend.repository.CelulaRepository;
import com.igreja360.backend.repository.MembroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membros")
@CrossOrigin(origins = "*")
public class MembroController {

    private final MembroRepository membroRepository;
    private final CelulaRepository celulaRepository;

    public MembroController(MembroRepository membroRepository, CelulaRepository celulaRepository) {
        this.membroRepository = membroRepository;
        this.celulaRepository = celulaRepository;
    }

@GetMapping
public List<MembroResponse> listar() {
    return membroRepository.findAll().stream().map(m -> {
        MembroResponse dto = new MembroResponse();

        dto.setId(m.getId());
        dto.setNome(m.getNome());
        dto.setEmail(m.getEmail());
        dto.setTelefone(m.getTelefone());

        if (m.getCelula() != null) {
            dto.setGc(m.getCelula().getNome());
        }

        return dto;
    }).toList();
}

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody MembroRequest request) {
        if (membroRepository.existsByCpf(request.getCpf())) {
            return ResponseEntity.badRequest().body("CPF já cadastrado");
        }

        if (membroRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        Membro membro = new Membro();

        membro.setNome(request.getNome());
        membro.setEmail(request.getEmail());
        membro.setCpf(request.getCpf());
        membro.setTelefone(request.getTelefone());
        membro.setBatizado(request.getBatizado());
        membro.setMembroDesde(request.getMembroDesde());
        membro.setVoluntario(request.getVoluntario());

        if (request.getGcId() != null) {
            Celula gc = celulaRepository.findById(request.getGcId())
                    .orElse(null);
            membro.setGc(gc);
        }

        return ResponseEntity.ok(membroRepository.save(membro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody MembroRequest request) {
        return membroRepository.findById(id)
                .map(membro -> {
                    membro.setNome(request.getNome());
                    membro.setEmail(request.getEmail());
                    membro.setCpf(request.getCpf());
                    membro.setTelefone(request.getTelefone());
                    membro.setBatizado(request.getBatizado());
                    membro.setMembroDesde(request.getMembroDesde());
                    membro.setVoluntario(request.getVoluntario());

                    if (request.getGcId() != null) {
                        Celula gc = celulaRepository.findById(request.getGcId())
                                .orElse(null);
                        membro.setGc(gc);
                    } else {
                        membro.setGc(null);
                    }

                    return ResponseEntity.ok(membroRepository.save(membro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (!membroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        membroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
