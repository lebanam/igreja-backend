package com.igreja360.backend.controller;

import com.igreja360.backend.dto.MinisterioRequest;
import com.igreja360.backend.dto.MinisterioResponse;
import com.igreja360.backend.service.MinisterioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ministerios")
@CrossOrigin(origins = "*")
public class MinisterioController {

    private final MinisterioService ministerioService;

    public MinisterioController(MinisterioService ministerioService) {
        this.ministerioService = ministerioService;
    }

    @GetMapping
    public ResponseEntity<List<MinisterioResponse>> listarTodos() {
        return ResponseEntity.ok(ministerioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MinisterioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ministerioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MinisterioResponse> criar(@RequestBody MinisterioRequest requestDTO) {
        return ResponseEntity.ok(ministerioService.criar(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MinisterioResponse> atualizar(
            @PathVariable Long id,
            @RequestBody MinisterioRequest requestDTO
    ) {
        return ResponseEntity.ok(ministerioService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ministerioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}