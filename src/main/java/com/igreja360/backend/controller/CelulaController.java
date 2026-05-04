package com.igreja360.backend.controller;

import com.igreja360.backend.dto.CelulaRequest;
import com.igreja360.backend.dto.CelulaResponse;
import com.igreja360.backend.model.Celula;
import com.igreja360.backend.repository.CelulaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/celulas")
@CrossOrigin(origins = "*")
public class CelulaController {

    private final CelulaRepository celulaRepository;

    public CelulaController(CelulaRepository celulaRepository) {
        this.celulaRepository = celulaRepository;
    }

    @GetMapping
    public List<CelulaResponse> listar() {
        return celulaRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return celulaRepository.findById(id)
                .map(celula -> ResponseEntity.ok(converterParaResponse(celula)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CelulaRequest request) {
        Celula celula = new Celula();

        celula.setNome(request.getNome());
        celula.setTema(request.getTema());
        celula.setQuando(request.getQuando());
        celula.setOnde(request.getOnde());
        celula.setLider(request.getLider());
        celula.setCoLider(request.getCoLider());

        Celula salva = celulaRepository.save(celula);

        return ResponseEntity.ok(converterParaResponse(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody CelulaRequest request
    ) {
        return celulaRepository.findById(id)
                .map(celula -> {
                    celula.setNome(request.getNome());
                    celula.setTema(request.getTema());
                    celula.setQuando(request.getQuando());
                    celula.setOnde(request.getOnde());
                    celula.setLider(request.getLider());
                    celula.setCoLider(request.getCoLider());

                    Celula atualizada = celulaRepository.save(celula);

                    return ResponseEntity.ok(converterParaResponse(atualizada));
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

    private CelulaResponse converterParaResponse(Celula celula) {
        CelulaResponse response = new CelulaResponse();

        response.setId(celula.getId());
        response.setNome(celula.getNome());
        response.setTema(celula.getTema());
        response.setQuando(celula.getQuando());
        response.setOnde(celula.getOnde());
        response.setLider(celula.getLider());
        response.setCoLider(celula.getCoLider());

        response.setMembros(List.of());

        return response;
    }
}