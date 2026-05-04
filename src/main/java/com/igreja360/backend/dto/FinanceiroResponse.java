package com.igreja360.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinanceiroResponse {

    private Long id;
    private String tipo;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private String categoria;
    private String observacoes;

    public FinanceiroResponse() {
    }

    public FinanceiroResponse(Long id, String tipo, String descricao, BigDecimal valor,
                                 LocalDate data, String categoria, String observacoes) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}