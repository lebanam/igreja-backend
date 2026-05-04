package com.igreja360.backend.dto;

import java.time.LocalDate;

public class MembroRequest {

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Boolean batizado;
    private LocalDate membroDesde;
    private Long celulaId; // ✅ CORRETO
    private Boolean voluntario;

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public Boolean getBatizado() { return batizado; }
    public LocalDate getMembroDesde() { return membroDesde; }
    public Long getCelulaId() { return celulaId; }
    public Boolean getVoluntario() { return voluntario; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setBatizado(Boolean batizado) { this.batizado = batizado; }
    public void setMembroDesde(LocalDate membroDesde) { this.membroDesde = membroDesde; }
    public void setCelulaId(Long celulaId) { this.celulaId = celulaId; }
    public void setVoluntario(Boolean voluntario) { this.voluntario = voluntario; }
}