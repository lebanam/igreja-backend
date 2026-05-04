package com.igreja360.backend.controller;

import com.igreja360.backend.dto.CelulaResumoResponse;
import com.igreja360.backend.dto.MembroRequest;
import com.igreja360.backend.dto.MembroResponse;
import com.igreja360.backend.model.Celula;
import com.igreja360.backend.model.Membro;
import com.igreja360.backend.repository.CelulaRepository;
import com.igreja360.backend.repository.MembroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return membroRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
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

        if (request.getCelulaId() != null) {
            Celula celula = celulaRepository.findById(request.getCelulaId()).orElse(null);

            if (celula == null) {
                return ResponseEntity.badRequest().body("Célula não encontrada");
            }

            membro.setCelula(celula);
        }

        Membro membroSalvo = membroRepository.save(membro);

        return ResponseEntity.ok(converterParaResponse(membroSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody MembroRequest request) {
        Membro membro = membroRepository.findById(id).orElse(null);

        if (membro == null) {
            return ResponseEntity.notFound().build();
        }

        membro.setNome(request.getNome());
        membro.setEmail(request.getEmail());
        membro.setCpf(request.getCpf());
        membro.setTelefone(request.getTelefone());
        membro.setBatizado(request.getBatizado());
        membro.setMembroDesde(request.getMembroDesde());
        membro.setVoluntario(request.getVoluntario());

        if (request.getCelulaId() != null) {
            Celula celula = celulaRepository.findById(request.getCelulaId()).orElse(null);

            if (celula == null) {
                return ResponseEntity.badRequest().body("Célula não encontrada");
            }

            membro.setCelula(celula);
        } else {
            membro.setCelula(null);
        }

        Membro membroAtualizado = membroRepository.save(membro);

        return ResponseEntity.ok(converterParaResponse(membroAtualizado));
    }

    @PatchMapping("/{id}/celula")
    public ResponseEntity<?> atualizarCelula(
            @PathVariable Long id,
            @RequestBody Map<String, Long> request
    ) {
        Membro membro = membroRepository.findById(id).orElse(null);

        if (membro == null) {
            return ResponseEntity.notFound().build();
        }

        Long celulaId = request.get("celulaId");

        if (celulaId == null) {
            membro.setCelula(null);
            Membro membroAtualizado = membroRepository.save(membro);
            return ResponseEntity.ok(converterParaResponse(membroAtualizado));
        }

        Celula celula = celulaRepository.findById(celulaId).orElse(null);

        if (celula == null) {
            return ResponseEntity.badRequest().body("Célula não encontrada");
        }

        membro.setCelula(celula);

        Membro membroAtualizado = membroRepository.save(membro);

        return ResponseEntity.ok(converterParaResponse(membroAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (!membroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        membroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private MembroResponse converterParaResponse(Membro membro) {
        MembroResponse dto = new MembroResponse();

        dto.setId(membro.getId());
        dto.setNome(membro.getNome());
        dto.setEmail(membro.getEmail());
        dto.setCpf(membro.getCpf());
        dto.setTelefone(membro.getTelefone());
        dto.setBatizado(membro.getBatizado());
        dto.setMembroDesde(membro.getMembroDesde());
        dto.setVoluntario(membro.getVoluntario());

        if (membro.getCelula() != null) {
            dto.setCelula(new CelulaResumoResponse(
                    membro.getCelula().getId(),
                    membro.getCelula().getNome()
            ));
        }

        return dto;
    }
}