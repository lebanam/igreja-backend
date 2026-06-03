package com.igreja360.backend.controller;

import com.igreja360.backend.dto.CelulaResumoResponse;
import com.igreja360.backend.dto.MembroRequest;
import com.igreja360.backend.dto.MembroResponse;
import com.igreja360.backend.dto.MembroResumoResponse;
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

        preencherDadosBasicos(membro, request);
        preencherDadosAdministrativos(membro, request);

        membro.setCadastroAprovado(true);

        ResponseEntity<?> erroRelacionamentos = preencherRelacionamentos(membro, request);
        if (erroRelacionamentos != null) {
            return erroRelacionamentos;
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

        preencherDadosBasicos(membro, request);
        preencherDadosAdministrativos(membro, request);

        ResponseEntity<?> erroRelacionamentos = preencherRelacionamentos(membro, request);
        if (erroRelacionamentos != null) {
            return erroRelacionamentos;
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

        preencherDadosAdministrativos(membro, request);

        ResponseEntity<?> erroRelacionamentos = preencherRelacionamentos(membro, request);
        if (erroRelacionamentos != null) {
            return erroRelacionamentos;
        }

        membro.setCadastroAprovado(true);

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

    private void preencherDadosBasicos(Membro membro, MembroRequest request) {
        membro.setNome(request.getNome());
        membro.setEmail(request.getEmail());
        membro.setCpf(request.getCpf());
        membro.setTelefone(request.getTelefone());
        membro.setDataNascimento(request.getDataNascimento());
        membro.setSexo(request.getSexo());
        membro.setEstadoCivil(request.getEstadoCivil());
        membro.setEndereco(request.getEndereco());
        membro.setInstagram(request.getInstagram());
    }

    private void preencherDadosAdministrativos(Membro membro, MembroRequest request) {
        membro.setBatizado(Boolean.TRUE.equals(request.getBatizado()));
        membro.setDataBatismo(Boolean.TRUE.equals(request.getBatizado()) ? request.getDataBatismo() : null);
        membro.setMembroDesde(request.getMembroDesde());
        membro.setVoluntario(Boolean.TRUE.equals(request.getVoluntario()));
        membro.setMinisteriosVoluntario(
                Boolean.TRUE.equals(request.getVoluntario())
                        ? request.getMinisteriosVoluntario()
                        : null
        );
        membro.setLiderCelula(Boolean.TRUE.equals(request.getLiderCelula()));
        membro.setLiderMinisterio(Boolean.TRUE.equals(request.getLiderMinisterio()));
    }

    private ResponseEntity<?> preencherRelacionamentos(Membro membro, MembroRequest request) {
        if (request.getCelulaId() != null) {
            Celula celula = celulaRepository.findById(request.getCelulaId()).orElse(null);

            if (celula == null) {
                return ResponseEntity.badRequest().body("Célula não encontrada");
            }

            membro.setCelula(celula);
        } else {
            membro.setCelula(null);
        }

        if (request.getPaiId() != null) {
            Membro pai = membroRepository.findById(request.getPaiId()).orElse(null);

            if (pai == null) {
                return ResponseEntity.badRequest().body("Pai não encontrado");
            }

            membro.setPai(pai);
        } else {
            membro.setPai(null);
        }

        if (request.getMaeId() != null) {
            Membro mae = membroRepository.findById(request.getMaeId()).orElse(null);

            if (mae == null) {
                return ResponseEntity.badRequest().body("Mãe não encontrada");
            }

            membro.setMae(mae);
        } else {
            membro.setMae(null);
        }

        if (request.getConjugeId() != null) {
            Membro conjuge = membroRepository.findById(request.getConjugeId()).orElse(null);

            if (conjuge == null) {
                return ResponseEntity.badRequest().body("Cônjuge não encontrado");
            }

            membro.setConjuge(conjuge);
        } else {
            membro.setConjuge(null);
        }

        if (request.getFilhosIds() != null && !request.getFilhosIds().isEmpty()) {
            List<Membro> filhos = membroRepository.findAllById(request.getFilhosIds());
            membro.setFilhos(filhos);
        } else {
            membro.setFilhos(List.of());
        }

        return null;
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
        dto.setMinisteriosVoluntario(membro.getMinisteriosVoluntario());
        dto.setLiderCelula(membro.getLiderCelula());
        dto.setLiderMinisterio(membro.getLiderMinisterio());

        if (membro.getCelula() != null) {
            dto.setCelula(new CelulaResumoResponse(
                    membro.getCelula().getId(),
                    membro.getCelula().getNome()
            ));
        }

        dto.setPai(converterParaResumo(membro.getPai()));
        dto.setMae(converterParaResumo(membro.getMae()));
        dto.setConjuge(converterParaResumo(membro.getConjuge()));

        if (membro.getFilhos() != null) {
            dto.setFilhos(
                    membro.getFilhos()
                            .stream()
                            .map(this::converterParaResumo)
                            .toList()
            );
        } else {
            dto.setFilhos(List.of());
        }

        return dto;
    }

    private MembroResumoResponse converterParaResumo(Membro membro) {
        if (membro == null) {
            return null;
        }

        return new MembroResumoResponse(
                membro.getId(),
                membro.getNome()
        );
    }
}