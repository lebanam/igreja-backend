package com.igreja360.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class RelatorioCelulaRequest {

    private LocalDate dataEncontro;
    private String tema;
    private Integer visitantes;
    private String observacoes;
    private List<PresencaCelulaRequest> presencas;

    public LocalDate getDataEncontro() { return dataEncontro; }
    public String getTema() { return tema; }
    public Integer getVisitantes() { return visitantes; }
    public String getObservacoes() { return observacoes; }
    public List<PresencaCelulaRequest> getPresencas() { return presencas; }

    public void setDataEncontro(LocalDate dataEncontro) { this.dataEncontro = dataEncontro; }
    public void setTema(String tema) { this.tema = tema; }
    public void setVisitantes(Integer visitantes) { this.visitantes = visitantes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public void setPresencas(List<PresencaCelulaRequest> presencas) { this.presencas = presencas; }
}