package com.igreja360.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;

    private String telefone;

    private LocalDate dataNascimento;

    private String sexo;

    private String estadoCivil;

    @Column(columnDefinition = "TEXT")
    private String endereco;

    private Boolean batizado;

    private LocalDate membroDesde;

    private Boolean voluntario;

    @ManyToOne
    @JoinColumn(name = "celula_id")
    private Celula celula;

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getSexo() { return sexo; }
    public String getEstadoCivil() { return estadoCivil; }
    public String getEndereco() { return endereco; }
    public Boolean getBatizado() { return batizado; }
    public LocalDate getMembroDesde() { return membroDesde; }
    public Boolean getVoluntario() { return voluntario; }
    public Celula getCelula() { return celula; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setBatizado(Boolean batizado) { this.batizado = batizado; }
    public void setMembroDesde(LocalDate membroDesde) { this.membroDesde = membroDesde; }
    public void setVoluntario(Boolean voluntario) { this.voluntario = voluntario; }
    public void setCelula(Celula celula) { this.celula = celula; }
}