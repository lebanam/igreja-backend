package com.igreja360.backend.controller;

import com.igreja360.backend.dto.LoginRequest;
import com.igreja360.backend.dto.LoginResponse;
import com.igreja360.backend.model.Usuario;
import com.igreja360.backend.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(401).body("E-mail ou senha inválidos");
        }

        if (!usuario.getAtivo()) {
            return ResponseEntity.status(403).body("Usuário inativo");
        }

        if (!usuario.getSenha().equals(request.getSenha())) {
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
}