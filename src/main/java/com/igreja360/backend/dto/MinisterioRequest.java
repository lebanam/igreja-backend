package com.igreja360.backend.dto;

public class MinisterioRequest {

    private String nome;
    private String descricao;

    public MinisterioRequest() {
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}