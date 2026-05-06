package com.igreja360.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class RelatorioCelula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEncontro;
    private String tema;
    private Integer visitantes;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    private LocalDateTime criadoEm;

    @ManyToOne
    @JoinColumn(name = "celula_id")
    private Celula celula;

    @OneToMany(mappedBy = "relatorio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresencaCelula> presencas;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public LocalDate getDataEncontro() { return dataEncontro; }
    public String getTema() { return tema; }
    public Integer getVisitantes() { return visitantes; }
    public String getObservacoes() { return observacoes; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public Celula getCelula() { return celula; }
    public List<PresencaCelula> getPresencas() { return presencas; }

    public void setId(Long id) { this.id = id; }
    public void setDataEncontro(LocalDate dataEncontro) { this.dataEncontro = dataEncontro; }
    public void setTema(String tema) { this.tema = tema; }
    public void setVisitantes(Integer visitantes) { this.visitantes = visitantes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
    public void setCelula(Celula celula) { this.celula = celula; }
    public void setPresencas(List<PresencaCelula> presencas) { this.presencas = presencas; }
}