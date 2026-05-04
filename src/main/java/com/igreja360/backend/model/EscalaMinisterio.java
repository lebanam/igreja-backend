package com.igreja360.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "escalas_ministerio")
public class EscalaMinisterio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    private LocalTime horario;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String textoEscala;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ministerio_id", nullable = false)
    private Ministerio ministerio;

    public EscalaMinisterio() {
    }

    public EscalaMinisterio(Long id, LocalDate data, LocalTime horario, String titulo,
                            String textoEscala, String observacoes, Ministerio ministerio) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.titulo = titulo;
        this.textoEscala = textoEscala;
        this.observacoes = observacoes;
        this.ministerio = ministerio;
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

    public Ministerio getMinisterio() {
        return ministerio;
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

    public void setMinisterio(Ministerio ministerio) {
        this.ministerio = ministerio;
    }
}