package com.igreja360.backend.controller;

import com.igreja360.backend.dto.CelulaRequest;
import com.igreja360.backend.model.Celula;
import com.igreja360.backend.model.Membro;
import com.igreja360.backend.repository.CelulaRepository;
import com.igreja360.backend.repository.MembroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/celulas")
@CrossOrigin(origins = "*")
public class CelulaController {

    private final CelulaRepository celulaRepository;
    private final MembroRepository membroRepository;

    public CelulaController(CelulaRepository celulaRepository, MembroRepository membroRepository) {
        this.celulaRepository = celulaRepository;
        this.membroRepository = membroRepository;
    }

    @GetMapping
    public List<Celula> listar() {
        return celulaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return celulaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CelulaRequest request) {
        Celula celula = new Celula();

        celula.setNome(request.getNome());
        celula.setFaixaEtaria(request.getFaixaEtaria());
        celula.setLider(request.getLider());
        celula.setCoLider(request.getCoLider());

        if (request.getMembrosIds() != null && !request.getMembrosIds().isEmpty()) {
            List<Membro> membros = membroRepository.findAllById(request.getMembrosIds());
            celula.setMembros(membros);
        }

        return ResponseEntity.ok(celulaRepository.save(celula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody CelulaRequest request) {
        return celulaRepository.findById(id)
                .map(celula -> {
                    celula.setNome(request.getNome());
                    celula.setFaixaEtaria(request.getFaixaEtaria());
                    celula.setLider(request.getLider());
                    celula.setCoLider(request.getCoLider());

                    if (request.getMembrosIds() != null) {
                        List<Membro> membros = membroRepository.findAllById(request.getMembrosIds());
                        celula.setMembros(membros);
                    }

                    return ResponseEntity.ok(celulaRepository.save(celula));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (!celulaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        celulaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}