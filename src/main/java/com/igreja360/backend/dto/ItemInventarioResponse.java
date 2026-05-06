package com.igreja360.backend.dto;

public class ItemInventarioResponse {

    private Long id;
    private String nome;
    private Integer quantidade;
    private Integer quantidadeMinima;
    private String localizacao;
    private String observacao;
    private String status;

    public ItemInventarioResponse(
            Long id,
            String nome,
            Integer quantidade,
            Integer quantidadeMinima,
            String localizacao,
            String observacao,
            String status
    ) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.localizacao = localizacao;
        this.observacao = observacao;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Integer getQuantidade() { return quantidade; }
    public Integer getQuantidadeMinima() { return quantidadeMinima; }
    public String getLocalizacao() { return localizacao; }
    public String getObservacao() { return observacao; }
    public String getStatus() { return status; }
}