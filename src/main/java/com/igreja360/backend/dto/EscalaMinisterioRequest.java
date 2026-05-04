package com.igreja360.backend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EscalaMinisterioRequest {

    private LocalDate data;
    private LocalTime horario;
    private String titulo;
    private String textoEscala;
    private String observacoes;
    private Long ministerioId;

    public EscalaMinisterioRequest() {
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTextoEscala() {
        return textoEscala;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public Long getMinisterioId() {
        return ministerioId;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTextoEscala(String textoEscala) {
        this.textoEscala = textoEscala;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setMinisterioId(Long ministerioId) {
        this.ministerioId = ministerioId;
    }
}