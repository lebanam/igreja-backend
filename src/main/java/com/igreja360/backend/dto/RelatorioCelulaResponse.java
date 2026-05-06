package com.igreja360.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class RelatorioCelulaResponse {

    private Long id;
    private LocalDate dataEncontro;
    private String tema;
    private Integer visitantes;
    private String observacoes;
    private LocalDateTime criadoEm;
    private Long celulaId;
    private String nomeCelula;
    private Integer totalPresentes;
    private Integer totalAusentes;
    private List<PresencaCelulaResponse> presencas;

    public RelatorioCelulaResponse(
            Long id,
            LocalDate dataEncontro,
            String tema,
            Integer visitantes,
            String observacoes,
            LocalDateTime criadoEm,
            Long celulaId,
            String nomeCelula,
            Integer totalPresentes,
            Integer totalAusentes,
            List<PresencaCelulaResponse> presencas
    ) {
        this.id = id;
        this.dataEncontro = dataEncontro;
        this.tema = tema;
        this.visitantes = visitantes;
        this.observacoes = observacoes;
        this.criadoEm = criadoEm;
        this.celulaId = celulaId;
        this.nomeCelula = nomeCelula;
        this.totalPresentes = totalPresentes;
        this.totalAusentes = totalAusentes;
        this.presencas = presencas;
    }

    public Long getId() { return id; }
    public LocalDate getDataEncontro() { return dataEncontro; }
    public String getTema() { return tema; }
    public Integer getVisitantes() { return visitantes; }
    public String getObservacoes() { return observacoes; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public Long getCelulaId() { return celulaId; }
    public String getNomeCelula() { return nomeCelula; }
    public Integer getTotalPresentes() { return totalPresentes; }
    public Integer getTotalAusentes() { return totalAusentes; }
    public List<PresencaCelulaResponse> getPresencas() { return presencas; }
}