package com.igreja360.backend.dto;

import java.util.List;

public class CategoriaInventarioResponse {

    private Long id;
    private String nome;
    private String descricao;
    private Integer totalItens;
    private List<ItemInventarioResponse> itens;

    public CategoriaInventarioResponse(
            Long id,
            String nome,
            String descricao,
            Integer totalItens,
            List<ItemInventarioResponse> itens
    ) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.totalItens = totalItens;
        this.itens = itens;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public Integer getTotalItens() { return totalItens; }
    public List<ItemInventarioResponse> getItens() { return itens; }
}