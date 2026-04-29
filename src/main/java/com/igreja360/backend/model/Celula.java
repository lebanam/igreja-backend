package com.igreja360.backend.model;

import jakarta.persistence.*;

@Entity
public class Celula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tema;
    private String quando;
    private String onde;
    private String lider;
    private String coLider;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTema() {
        return tema;
    }

    public String getQuando() {
        return quando;
    }

    public String getOnde() {
        return onde;
    }

    public String getLider() {
        return lider;
    }

    public String getCoLider() {
        return coLider;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setQuando(String quando) {
        this.quando = quando;
    }

    public void setOnde(String onde) {
        this.onde = onde;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public void setCoLider(String coLider) {
        this.coLider = coLider;
    }
}