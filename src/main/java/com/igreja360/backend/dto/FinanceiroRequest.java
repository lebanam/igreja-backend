package com.igreja360.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinanceiroRequest {

    private String tipo;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private String categoria;
    private String observacoes;

    public FinanceiroRequest() {
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