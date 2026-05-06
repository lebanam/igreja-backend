package com.igreja360.backend.controller;

import com.igreja360.backend.dto.*;
import com.igreja360.backend.service.InventarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/categorias")
    public List<CategoriaInventarioResponse> listarCategorias() {
        return inventarioService.listarCategorias();
    }

    @GetMapping("/categorias/{id}")
    public CategoriaInventarioResponse buscarCategoria(@PathVariable Long id) {
        return inventarioService.buscarCategoria(id);
    }

    @PostMapping("/categorias")
    public CategoriaInventarioResponse criarCategoria(@RequestBody CategoriaInventarioRequest request) {
        return inventarioService.criarCategoria(request);
    }

    @PutMapping("/categorias/{id}")
    public CategoriaInventarioResponse atualizarCategoria(
            @PathVariable Long id,
            @RequestBody CategoriaInventarioRequest request
    ) {
        return inventarioService.atualizarCategoria(id, request);
    }

    @DeleteMapping("/categorias/{id}")
    public void excluirCategoria(@PathVariable Long id) {
        inventarioService.excluirCategoria(id);
    }

    @PostMapping("/categorias/{categoriaId}/itens")
    public ItemInventarioResponse criarItem(
            @PathVariable Long categoriaId,
            @RequestBody ItemInventarioRequest request
    ) {
        return inventarioService.criarItem(categoriaId, request);
    }

    @PutMapping("/categorias/{categoriaId}/itens")
    public List<ItemInventarioResponse> salvarItensDaCategoria(
            @PathVariable Long categoriaId,
            @RequestBody List<ItemInventarioRequest> requests
    ) {
        return inventarioService.salvarItensDaCategoria(categoriaId, requests);
    }

    @DeleteMapping("/itens/{id}")
    public void excluirItem(@PathVariable Long id) {
        inventarioService.excluirItem(id);
    }
}