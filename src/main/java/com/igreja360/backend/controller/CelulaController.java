package com.igreja360.backend.controller;

import com.igreja360.backend.dto.CelulaRequest;
import com.igreja360.backend.dto.CelulaResponse;
import com.igreja360.backend.dto.CelulaResumoResponse;
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

    public CelulaController(
            CelulaRepository celulaRepository,
            MembroRepository membroRepository
    ) {
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

    @GetMapping("/resumo")
    public List<CelulaResumoResponse> listarResumo() {
        return celulaRepository.findAll()
                .stream()
                .map(c -> new CelulaResumoResponse(c.getId(), c.getNome()))
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
        Celula celula = celulaRepository.findById(id).orElse(null);

        if (celula == null) {
            return ResponseEntity.notFound().build();
        }

        List<Membro> membros = membroRepository.findByCelulaId(id);

        for (Membro membro : membros) {
            membro.setCelula(null);
        }

        membroRepository.saveAll(membros);
        celulaRepository.delete(celula);

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

        List<MembroResumoResponse> membros = membroRepository
                .findByCelulaId(celula.getId())
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