package com.igreja360.backend.controller;

import com.igreja360.backend.dto.CelulaResumoResponse;
import com.igreja360.backend.dto.MembroRequest;
import com.igreja360.backend.dto.MembroResponse;
import com.igreja360.backend.model.Celula;
import com.igreja360.backend.model.Membro;
import com.igreja360.backend.model.Usuario;
import com.igreja360.backend.repository.CelulaRepository;
import com.igreja360.backend.repository.MembroRepository;
import com.igreja360.backend.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/membros")
@CrossOrigin(origins = "*")
public class MembroController {

    private final MembroRepository membroRepository;
    private final CelulaRepository celulaRepository;
    private final UsuarioRepository usuarioRepository;

    public MembroController(
            MembroRepository membroRepository,
            CelulaRepository celulaRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.membroRepository = membroRepository;
        this.celulaRepository = celulaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<MembroResponse> listar() {
        return membroRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @GetMapping("/pendentes")
    public List<MembroResponse> listarPendentes() {
        return membroRepository.findAll()
                .stream()
                .filter(membro -> Boolean.FALSE.equals(membro.getCadastroAprovado()))
                .map(this::converterParaResponse)
                .toList();
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody MembroRequest request) {
        if (request.getEmail() != null && membroRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        Membro membro = new Membro();

        membro.setNome(request.getNome());
        membro.setEmail(request.getEmail());
        membro.setCpf(request.getCpf());
        membro.setTelefone(request.getTelefone());
        membro.setDataNascimento(request.getDataNascimento());
        membro.setSexo(request.getSexo());
        membro.setEstadoCivil(request.getEstadoCivil());
        membro.setEndereco(request.getEndereco());
        membro.setInstagram(request.getInstagram());

        membro.setBatizado(request.getBatizado());
        membro.setDataBatismo(request.getDataBatismo());
        membro.setMembroDesde(request.getMembroDesde());
        membro.setVoluntario(request.getVoluntario());
        membro.setCadastroAprovado(true);

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
        membro.setDataNascimento(request.getDataNascimento());
        membro.setSexo(request.getSexo());
        membro.setEstadoCivil(request.getEstadoCivil());
        membro.setEndereco(request.getEndereco());
        membro.setInstagram(request.getInstagram());

        membro.setBatizado(request.getBatizado());
        membro.setDataBatismo(request.getDataBatismo());
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

    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<?> aprovarCadastro(
            @PathVariable Long id,
            @RequestBody MembroRequest request
    ) {
        Membro membro = membroRepository.findById(id).orElse(null);

        if (membro == null) {
            return ResponseEntity.notFound().build();
        }

        membro.setBatizado(request.getBatizado());
        membro.setDataBatismo(request.getDataBatismo());
        membro.setVoluntario(request.getVoluntario());
        membro.setMembroDesde(request.getMembroDesde());
        membro.setCadastroAprovado(true);

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

        Usuario usuario = usuarioRepository.findByEmail(membro.getEmail()).orElse(null);

        if (usuario != null) {
            usuario.setAtivo(true);
            usuarioRepository.save(usuario);
        }

        return ResponseEntity.ok(converterParaResponse(membroAtualizado));
    }

    @PatchMapping("/{id}/reprovar")
    public ResponseEntity<?> reprovarCadastro(@PathVariable Long id) {
        Membro membro = membroRepository.findById(id).orElse(null);

        if (membro == null) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioRepository.findByEmail(membro.getEmail()).orElse(null);

        if (usuario != null) {
            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
        }

        membro.setCadastroAprovado(false);
        membroRepository.save(membro);

        return ResponseEntity.ok("Cadastro reprovado");
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

        dto.setDataNascimento(membro.getDataNascimento());
        dto.setSexo(membro.getSexo());
        dto.setEstadoCivil(membro.getEstadoCivil());
        dto.setEndereco(membro.getEndereco());
        dto.setInstagram(membro.getInstagram());
        dto.setTipoCadastro(membro.getTipoCadastro());
        dto.setCadastroAprovado(membro.getCadastroAprovado());

        if (membro.getDataNascimento() != null) {
            dto.setIdade(Period.between(
                    membro.getDataNascimento(),
                    LocalDate.now()
            ).getYears());
        }

        dto.setBatizado(membro.getBatizado());
        dto.setDataBatismo(membro.getDataBatismo());
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