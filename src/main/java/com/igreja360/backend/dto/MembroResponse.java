package com.igreja360.backend.dto;

import java.time.LocalDate;

public class MembroResponse {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Boolean batizado;
    private LocalDate membroDesde;
    private Boolean voluntario;
    private CelulaResumoResponse celula;

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public Boolean getBatizado() { return batizado; }
    public LocalDate getMembroDesde() { return membroDesde; }
    public Boolean getVoluntario() { return voluntario; }
    public CelulaResumoResponse getCelula() { return celula; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setBatizado(Boolean batizado) { this.batizado = batizado; }
    public void setMembroDesde(LocalDate membroDesde) { this.membroDesde = membroDesde; }
    public void setVoluntario(Boolean voluntario) { this.voluntario = voluntario; }
    public void setCelula(CelulaResumoResponse celula) { this.celula = celula; }
}