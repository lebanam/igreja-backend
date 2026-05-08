package com.igreja360.backend.controller;

import com.igreja360.backend.dto.IgrejaRequest;
import com.igreja360.backend.dto.IgrejaResponse;
import com.igreja360.backend.service.IgrejaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/igreja")
public class IgrejaController {

    private final IgrejaService igrejaService;

    public IgrejaController(IgrejaService igrejaService) {
        this.igrejaService = igrejaService;
    }

    @GetMapping
    public IgrejaResponse buscarConfiguracao() {
        return igrejaService.buscarConfiguracao();
    }

    @PutMapping
    public IgrejaResponse atualizarConfiguracao(@RequestBody IgrejaRequest request) {
        return igrejaService.atualizarConfiguracao(request);
    }
}