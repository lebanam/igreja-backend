package com.igreja360.backend.dto;

import java.util.List;

public class CelulaRequest {

    private String nome;
    private String faixaEtaria;
    private String lider;
    private String coLider;
    private List<Long> membrosIds;

    public String getNome() {
        return nome;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public String getLider() {
        return lider;
    }

    public String getCoLider() {
        return coLider;
    }

    public List<Long> getMembrosIds() {
        return membrosIds;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public void setCoLider(String coLider) {
        this.coLider = coLider;
    }

    public void setMembrosIds(List<Long> membrosIds) {
        this.membrosIds = membrosIds;
    }
}