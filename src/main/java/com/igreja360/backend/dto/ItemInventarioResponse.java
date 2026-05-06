package com.igreja360.backend.dto;

public class ItemInventarioResponse {

    private Long id;
    private String nome;
    private Integer quantidade;
    private String observacao;

    public ItemInventarioResponse(
            Long id,
            String nome,
            Integer quantidade,
            String observacao
    ) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.observacao = observacao;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Integer getQuantidade() { return quantidade; }
    public String getObservacao() { return observacao; }
}