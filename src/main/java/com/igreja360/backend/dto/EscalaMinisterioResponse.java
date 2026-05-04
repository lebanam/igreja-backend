package com.igreja360.backend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EscalaMinisterioResponse {

    private Long id;
    private LocalDate data;
    private LocalTime horario;
    private String titulo;
    private String textoEscala;
    private String observacoes;
    private Long ministerioId;
    private String ministerioNome;

    public EscalaMinisterioResponse() {
    }

    public EscalaMinisterioResponse(Long id, LocalDate data, LocalTime horario, String titulo,
                                       String textoEscala, String observacoes,
                                       Long ministerioId, String ministerioNome) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.titulo = titulo;
        this.textoEscala = textoEscala;
        this.observacoes = observacoes;
        this.ministerioId = ministerioId;
        this.ministerioNome = ministerioNome;
    }

    public Long getId() {
        return id;
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

    public String getMinisterioNome() {
        return ministerioNome;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setMinisterioNome(String ministerioNome) {
        this.ministerioNome = ministerioNome;
    }
}