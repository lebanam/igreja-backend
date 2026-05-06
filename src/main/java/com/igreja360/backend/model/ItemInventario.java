package com.igreja360.backend.model;

import jakarta.persistence.*;

@Entity
public class ItemInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer quantidade;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaInventario categoria;

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Integer getQuantidade() { return quantidade; }
    public String getObservacao() { return observacao; }
    public CategoriaInventario getCategoria() { return categoria; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
    public void setCategoria(CategoriaInventario categoria) { this.categoria = categoria; }
}