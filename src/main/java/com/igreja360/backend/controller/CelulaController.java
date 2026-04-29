package com.igreja360.backend.controller;

import com.igreja360.backend.dto.CelulaRequest;
import com.igreja360.backend.dto.CelulaResponse;
import com.igreja360.backend.dto.MembroResumoResponse;
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
    public List<CelulaResponse> listar() {
        return celulaRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return celulaRepository.findById(id)
                .map(c -> ResponseEntity.ok(converterParaResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CelulaRequest request) {
        Celula celula = new Celula();
        preencherCelula(celula, request);

        Celula salva = celulaRepository.save(celula);
        return ResponseEntity.ok(converterParaResponse(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody CelulaRequest request) {
        return celulaRepository.findById(id)
                .map(celula -> {
                    preencherCelula(celula, request);
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

    private void preencherCelula(Celula celula, CelulaRequest request) {
        celula.setNome(request.getNome());
        celula.setTema(request.getTema());
        celula.setQuando(request.getQuando());
        celula.setOnde(request.getOnde());
        celula.setLider(request.getLider());
        celula.setCoLider(request.getCoLider());

        if (request.getMembrosIds() != null) {
            List<Membro> membros = membroRepository.findAllById(request.getMembrosIds());
            celula.setMembros(membros);
        }
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

        List<MembroResumoResponse> membros = celula.getMembros()
                .stream()
                .map(m -> {
                    MembroResumoResponse r = new MembroResumoResponse();
                    r.setId(m.getId());
                    r.setNome(m.getNome());
                    return r;
                })
                .toList();

        response.setMembros(membros);

        return response;
    }
}