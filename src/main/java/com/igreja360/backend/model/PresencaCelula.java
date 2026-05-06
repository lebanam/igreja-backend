package com.igreja360.backend.model;

import jakarta.persistence.*;

@Entity
public class PresencaCelula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean presente;

    @ManyToOne
    @JoinColumn(name = "relatorio_id")
    private RelatorioCelula relatorio;

    @ManyToOne
    @JoinColumn(name = "membro_id")
    private Membro membro;

    public Long getId() { return id; }
    public Boolean getPresente() { return presente; }
    public RelatorioCelula getRelatorio() { return relatorio; }
    public Membro getMembro() { return membro; }

    public void setId(Long id) { this.id = id; }
    public void setPresente(Boolean presente) { this.presente = presente; }
    public void setRelatorio(RelatorioCelula relatorio) { this.relatorio = relatorio; }
    public void setMembro(Membro membro) { this.membro = membro; }
}