package com.igreja360.backend.dto;

import java.time.LocalDate;

public class MembroRequest {

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    // ✅ NOVOS CAMPOS
    private LocalDate dataNascimento;
    private String sexo;
    private String estadoCivil;
    private String endereco;

    private Boolean batizado;
    private LocalDate membroDesde;
    private Long celulaId;
    private Boolean voluntario;

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }

    // ✅ GETS NOVOS
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getSexo() { return sexo; }
    public String getEstadoCivil() { return estadoCivil; }
    public String getEndereco() { return endereco; }

    public Boolean getBatizado() { return batizado; }
    public LocalDate getMembroDesde() { return membroDesde; }
    public Long getCelulaId() { return celulaId; }
    public Boolean getVoluntario() { return voluntario; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    // ✅ SETS NOVOS
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public void setBatizado(Boolean batizado) { this.batizado = batizado; }
    public void setMembroDesde(LocalDate membroDesde) { this.membroDesde = membroDesde; }
    public void setCelulaId(Long celulaId) { this.celulaId = celulaId; }
    public void setVoluntario(Boolean voluntario) { this.voluntario = voluntario; }
}