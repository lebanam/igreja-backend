package com.igreja360.backend.controller;

import com.igreja360.backend.dto.RelatorioCelulaRequest;
import com.igreja360.backend.dto.RelatorioCelulaResponse;
import com.igreja360.backend.service.RelatorioCelulaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/celulas")
public class RelatorioCelulaController {

    private final RelatorioCelulaService relatorioService;

    public RelatorioCelulaController(RelatorioCelulaService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @PostMapping("/{celulaId}/relatorios")
    public RelatorioCelulaResponse criar(
            @PathVariable Long celulaId,
            @RequestBody RelatorioCelulaRequest request
    ) {
        return relatorioService.criar(celulaId, request);
    }

    @GetMapping("/{celulaId}/relatorios")
    public List<RelatorioCelulaResponse> listarPorCelula(@PathVariable Long celulaId) {
        return relatorioService.listarPorCelula(celulaId);
    }

    @GetMapping("/relatorios/{id}")
    public RelatorioCelulaResponse buscarPorId(@PathVariable Long id) {
        return relatorioService.buscarPorId(id);
    }

    @DeleteMapping("/relatorios/{id}")
    public void excluir(@PathVariable Long id) {
        relatorioService.excluir(id);
    }
}