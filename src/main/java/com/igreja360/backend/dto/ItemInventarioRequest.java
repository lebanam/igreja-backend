package com.igreja360.backend.dto;

public class ItemInventarioRequest {

    private Long id;
    private String nome;
    private Integer quantidade;
    private Integer quantidadeMinima;
    private String localizacao;
    private String observacao;

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Integer getQuantidade() { return quantidade; }
    public Integer getQuantidadeMinima() { return quantidadeMinima; }
    public String getLocalizacao() { return localizacao; }
    public String getObservacao() { return observacao; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public void setQuantidadeMinima(Integer quantidadeMinima) { this.quantidadeMinima = quantidadeMinima; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}