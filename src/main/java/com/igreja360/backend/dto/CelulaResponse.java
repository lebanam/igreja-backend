package com.igreja360.backend.dto;

import java.util.List;

public class CelulaResponse {

    private Long id;
    private String nome;
    private String tema;
    private String quando;
    private String onde;
    private String lider;
    private String coLider;
    private List<MembroResumoResponse> membros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getQuando() {
        return quando;
    }

    public void setQuando(String quando) {
        this.quando = quando;
    }

    public String getOnde() {
        return onde;
    }

    public void setOnde(String onde) {
        this.onde = onde;
    }

    public String getLider() {
        return lider;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public String getCoLider() {
        return coLider;
    }

    public void setCoLider(String coLider) {
        this.coLider = coLider;
    }

    public List<MembroResumoResponse> getMembros() {
        return membros;
    }

    public void setMembros(List<MembroResumoResponse> membros) {
        this.membros = membros;
    }
}