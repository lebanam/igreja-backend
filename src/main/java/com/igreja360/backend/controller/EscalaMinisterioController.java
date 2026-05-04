package com.igreja360.backend.controller;

import com.igreja360.backend.dto.EscalaMinisterioRequest;
import com.igreja360.backend.dto.EscalaMinisterioResponse;
import com.igreja360.backend.service.EscalaMinisterioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EscalaMinisterioController {

    private final EscalaMinisterioService escalaService;

    public EscalaMinisterioController(EscalaMinisterioService escalaService) {
        this.escalaService = escalaService;
    }

    @GetMapping("/ministerios/{ministerioId}/escalas")
    public ResponseEntity<List<EscalaMinisterioResponse>> listarPorMinisterio(
            @PathVariable Long ministerioId
    ) {
        return ResponseEntity.ok(escalaService.listarPorMinisterio(ministerioId));
    }

    @GetMapping("/escalas/{id}")
    public ResponseEntity<EscalaMinisterioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(escalaService.buscarPorId(id));
    }

    @PostMapping("/escalas")
    public ResponseEntity<EscalaMinisterioResponse> criar(
            @RequestBody EscalaMinisterioRequest requestDTO
    ) {
        return ResponseEntity.ok(escalaService.criar(requestDTO));
    }

    @PutMapping("/escalas/{id}")
    public ResponseEntity<EscalaMinisterioResponse> atualizar(
            @PathVariable Long id,
            @RequestBody EscalaMinisterioRequest requestDTO
    ) {
        return ResponseEntity.ok(escalaService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/escalas/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        escalaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}