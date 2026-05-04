package com.igreja360.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "escala_participantes")
public class EscalaParticipante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String funcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escala_ministerio_id", nullable = false)
    private EscalaMinisterio escalaMinisterio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membro_id", nullable = false)
    private Membro membro;

    public EscalaParticipante() {
    }

    public EscalaParticipante(Long id, String funcao, EscalaMinisterio escalaMinisterio, Membro membro) {
        this.id = id;
        this.funcao = funcao;
        this.escalaMinisterio = escalaMinisterio;
        this.membro = membro;
    }

    public Long getId() {
        return id;
    }

    public String getFuncao() {
        return funcao;
    }

    public EscalaMinisterio getEscalaMinisterio() {
        return escalaMinisterio;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setEscalaMinisterio(EscalaMinisterio escalaMinisterio) {
        this.escalaMinisterio = escalaMinisterio;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }
}