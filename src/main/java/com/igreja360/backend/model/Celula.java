package com.igreja360.backend.model;

import com.igreja360.backend.model.Membro;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Celula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String faixaEtaria;
    private String lider;
    private String coLider;

    @OneToMany
    @JoinTable(
            name = "celula_membros",
            joinColumns = @JoinColumn(name = "celula_id"),
            inverseJoinColumns = @JoinColumn(name = "membro_id")
    )
    private List<Membro> membros = new ArrayList<>();

    public Long getId() {
        return id;
    }

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

    public List<Membro> getMembros() {
        return membros;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }
}