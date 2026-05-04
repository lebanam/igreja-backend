package com.igreja360.backend.controller;

import com.igreja360.backend.dto.FinanceiroRequest;
import com.igreja360.backend.dto.FinanceiroResponse;
import com.igreja360.backend.service.FinanceiroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financeiro")
@CrossOrigin(origins = "*")
public class FinanceiroController {

    private final FinanceiroService financeiroService;

    public FinanceiroController(FinanceiroService financeiroService) {
        this.financeiroService = financeiroService;
    }

    @GetMapping
    public ResponseEntity<List<FinanceiroResponse>> listarTodos() {
        return ResponseEntity.ok(financeiroService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<FinanceiroResponse> criar(@RequestBody FinanceiroRequest requestDTO) {
        return ResponseEntity.ok(financeiroService.criar(requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        financeiroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}