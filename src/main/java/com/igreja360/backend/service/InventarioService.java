package com.igreja360.backend.service;

import com.igreja360.backend.dto.*;
import com.igreja360.backend.model.CategoriaInventario;
import com.igreja360.backend.model.ItemInventario;
import com.igreja360.backend.repository.CategoriaInventarioRepository;
import com.igreja360.backend.repository.ItemInventarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventarioService {

    private final CategoriaInventarioRepository categoriaRepository;
    private final ItemInventarioRepository itemRepository;

    public InventarioService(
            CategoriaInventarioRepository categoriaRepository,
            ItemInventarioRepository itemRepository
    ) {
        this.categoriaRepository = categoriaRepository;
        this.itemRepository = itemRepository;
    }

    public List<CategoriaInventarioResponse> listarCategorias() {
        return categoriaRepository.findAllByOrderByNomeAsc()
                .stream()
                .map(this::converterCategoriaParaResponse)
                .toList();
    }

    public CategoriaInventarioResponse buscarCategoria(Long id) {
        CategoriaInventario categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return converterCategoriaParaResponse(categoria);
    }

    public CategoriaInventarioResponse criarCategoria(CategoriaInventarioRequest request) {
        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new RuntimeException("Informe o nome da categoria");
        }

        CategoriaInventario categoria = new CategoriaInventario();
        categoria.setNome(request.getNome().trim());
        categoria.setDescricao(request.getDescricao());

        CategoriaInventario salva = categoriaRepository.save(categoria);
        return converterCategoriaParaResponse(salva);
    }

    public CategoriaInventarioResponse atualizarCategoria(Long id, CategoriaInventarioRequest request) {
        CategoriaInventario categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new RuntimeException("Informe o nome da categoria");
        }

        categoria.setNome(request.getNome().trim());
        categoria.setDescricao(request.getDescricao());

        CategoriaInventario salva = categoriaRepository.save(categoria);
        return converterCategoriaParaResponse(salva);
    }

    public void excluirCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }

        categoriaRepository.deleteById(id);
    }

    public ItemInventarioResponse criarItem(Long categoriaId, ItemInventarioRequest request) {
        CategoriaInventario categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new RuntimeException("Informe o nome do item");
        }

        ItemInventario item = new ItemInventario();
        item.setCategoria(categoria);
        item.setNome(request.getNome().trim());
        item.setQuantidade(normalizarNumero(request.getQuantidade()));
        item.setQuantidadeMinima(normalizarNumero(request.getQuantidadeMinima()));
        item.setLocalizacao(request.getLocalizacao());
        item.setObservacao(request.getObservacao());

        ItemInventario salvo = itemRepository.save(item);
        return converterItemParaResponse(salvo);
    }

    public List<ItemInventarioResponse> salvarItensDaCategoria(
            Long categoriaId,
            List<ItemInventarioRequest> requests
    ) {
        CategoriaInventario categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        List<ItemInventario> itensSalvos = new ArrayList<>();

        for (ItemInventarioRequest request : requests) {
            if (request.getNome() == null || request.getNome().trim().isEmpty()) {
                throw new RuntimeException("Existe item sem nome informado");
            }

            ItemInventario item;

            if (request.getId() != null) {
                item = itemRepository.findById(request.getId())
                        .orElseThrow(() -> new RuntimeException("Item não encontrado"));

                if (!item.getCategoria().getId().equals(categoriaId)) {
                    throw new RuntimeException("Item não pertence a esta categoria");
                }
            } else {
                item = new ItemInventario();
                item.setCategoria(categoria);
            }

            item.setNome(request.getNome().trim());
            item.setQuantidade(normalizarNumero(request.getQuantidade()));
            item.setQuantidadeMinima(normalizarNumero(request.getQuantidadeMinima()));
            item.setLocalizacao(request.getLocalizacao());
            item.setObservacao(request.getObservacao());

            itensSalvos.add(itemRepository.save(item));
        }

        return itensSalvos.stream()
                .map(this::converterItemParaResponse)
                .toList();
    }

    public void excluirItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item não encontrado");
        }

        itemRepository.deleteById(id);
    }

    private CategoriaInventarioResponse converterCategoriaParaResponse(CategoriaInventario categoria) {
        List<ItemInventarioResponse> itens = categoria.getItens() == null
                ? List.of()
                : categoria.getItens()
                  .stream()
                  .sorted((a, b) -> (a.getNome() == null ? "" : a.getNome())
                                    .compareToIgnoreCase(b.getNome() == null ? "" : b.getNome()))
                  .map(this::converterItemParaResponse)
                  .toList();

        return new CategoriaInventarioResponse(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                itens.size(),
                itens
        );
    }

    private ItemInventarioResponse converterItemParaResponse(ItemInventario item) {
        int quantidade = normalizarNumero(item.getQuantidade());
        int minima = normalizarNumero(item.getQuantidadeMinima());

        return new ItemInventarioResponse(
                item.getId(),
                item.getNome(),
                quantidade,
                minima,
                item.getLocalizacao(),
                item.getObservacao(),
                calcularStatus(quantidade, minima)
        );
    }

    private Integer normalizarNumero(Integer valor) {
        if (valor == null || valor < 0) {
            return 0;
        }

        return valor;
    }

    private String calcularStatus(Integer quantidade, Integer quantidadeMinima) {
        int qtd = normalizarNumero(quantidade);
        int minima = normalizarNumero(quantidadeMinima);

        if (qtd == 0) {
            return "SEM_ESTOQUE";
        }

        if (minima > 0 && qtd <= minima) {
            return "BAIXO_ESTOQUE";
        }

        return "OK";
    }
}