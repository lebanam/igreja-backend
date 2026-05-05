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

        System.out.println("Tentativa de login");
        System.out.println("Email recebido: " + request.getEmail());
        System.out.println("Senha recebida: " + request.getSenha());

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (usuario == null) {
            System.out.println("Usuário NÃO encontrado no banco");
            return ResponseEntity.status(401).body("E-mail ou senha inválidos");
        }

        System.out.println("Usuário encontrado: " + usuario.getEmail());
        System.out.println("Senha banco: " + usuario.getSenha());

        if (!usuario.getAtivo()) {
            return ResponseEntity.status(403).body("Usuário inativo");
        }

        if (!usuario.getSenha().equals(request.getSenha())) {
            System.out.println("Senha NÃO bate");
            return ResponseEntity.status(401).body("E-mail ou senha inválidos");
        }

        System.out.println("Login OK");

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