package com.igreja360.backend.controller;

import com.igreja360.backend.dto.CadastroUsuarioRequest;
import com.igreja360.backend.dto.CadastroUsuarioResponse;
import com.igreja360.backend.dto.LoginRequest;
import com.igreja360.backend.dto.LoginResponse;
import com.igreja360.backend.model.Membro;
import com.igreja360.backend.model.Role;
import com.igreja360.backend.model.TipoCadastro;
import com.igreja360.backend.model.Usuario;
import com.igreja360.backend.repository.MembroRepository;
import com.igreja360.backend.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final MembroRepository membroRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UsuarioRepository usuarioRepository,
            MembroRepository membroRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.membroRepository = membroRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(401).body("E-mail ou senha inválidos");
        }

        if (Boolean.FALSE.equals(usuario.getAtivo())) {
            return ResponseEntity.status(403).body("Seu cadastro ainda não foi aprovado pela administração");
        }

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            return ResponseEntity.status(401).body("E-mail ou senha inválidos");
        }

        return ResponseEntity.ok(
                new LoginResponse(
                        "token-temporario",
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getRole().name()
                )
        );
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CadastroUsuarioRequest request) {
        if (request.getNome() == null || request.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("Nome completo é obrigatório");
        }

        if (request.getTelefone() == null || request.getTelefone().isBlank()) {
            return ResponseEntity.badRequest().body("Celular é obrigatório");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("E-mail é obrigatório");
        }

        if (request.getDataNascimento() == null) {
            return ResponseEntity.badRequest().body("Data de nascimento é obrigatória");
        }

        if (request.getSexo() == null || request.getSexo().isBlank()) {
            return ResponseEntity.badRequest().body("Sexo é obrigatório");
        }

        if (request.getEstadoCivil() == null || request.getEstadoCivil().isBlank()) {
            return ResponseEntity.badRequest().body("Estado civil é obrigatório");
        }

        if (request.getEndereco() == null || request.getEndereco().isBlank()) {
            return ResponseEntity.badRequest().body("Endereço é obrigatório");
        }

        if (request.getTipoCadastro() == null || request.getTipoCadastro().isBlank()) {
            return ResponseEntity.badRequest().body("Tipo de cadastro é obrigatório");
        }

        if (request.getSenha() == null || request.getSenha().length() < 6) {
            return ResponseEntity.badRequest().body("A senha deve ter no mínimo 6 caracteres");
        }

        String emailNormalizado = request.getEmail().trim().toLowerCase();

        if (usuarioRepository.existsByEmail(emailNormalizado)) {
            return ResponseEntity.badRequest().body("Já existe um usuário com este e-mail");
        }

        if (membroRepository.existsByEmail(emailNormalizado)) {
            return ResponseEntity.badRequest().body("Já existe um cadastro com este e-mail");
        }

        TipoCadastro tipoCadastro;

        try {
            tipoCadastro = TipoCadastro.valueOf(request.getTipoCadastro().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Tipo de cadastro inválido");
        }

        Membro membro = new Membro();
        membro.setNome(request.getNome().trim());
        membro.setEmail(emailNormalizado);
        membro.setTelefone(request.getTelefone().trim());
        membro.setDataNascimento(request.getDataNascimento());
        membro.setSexo(request.getSexo());
        membro.setEstadoCivil(request.getEstadoCivil());
        membro.setEndereco(request.getEndereco());
        membro.setInstagram(request.getInstagram());
        membro.setTipoCadastro(tipoCadastro);

        membro.setCadastroAprovado(false);

        // Campos administrativos, preenchidos posteriormente pelo ADMIN
        membro.setBatizado(false);
        membro.setVoluntario(false);
        membro.setMembroDesde(null);

        membro = membroRepository.save(membro);

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome().trim());
        usuario.setEmail(emailNormalizado);
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setRole(Role.MEMBRO);

        // Usuário nasce inativo até aprovação do ADMIN
        usuario.setAtivo(false);

        usuario.setMembro(membro);
        usuario.setPrimeiroAcesso(true);
        usuario.setAvatarUrl(null);

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(
                new CadastroUsuarioResponse(
                        "Cadastro enviado com sucesso. Aguarde aprovação da administração.",
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getRole().name()
                )
        );
    }
}