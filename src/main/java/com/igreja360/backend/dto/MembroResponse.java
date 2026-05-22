package com.igreja360.backend.dto;

import com.igreja360.backend.model.TipoCadastro;

import java.time.LocalDate;

public class MembroResponse {

    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private String telefone;

    private LocalDate dataNascimento;

    private Integer idade;

    private String sexo;

    private String estadoCivil;

    private String endereco;

    private String instagram;

    private TipoCadastro tipoCadastro;

    private Boolean cadastroAprovado;

    private Boolean batizado;

    private LocalDate membroDesde;

    private Boolean voluntario;

    private CelulaResumoResponse celula;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getInstagram() {
        return instagram;
    }

    public TipoCadastro getTipoCadastro() {
        return tipoCadastro;
    }

    public Boolean getCadastroAprovado() {
        return cadastroAprovado;
    }

    public Boolean getBatizado() {
        return batizado;
    }

    public LocalDate getMembroDesde() {
        return membroDesde;
    }

    public Boolean getVoluntario() {
        return voluntario;
    }

    public CelulaResumoResponse getCelula() {
        return celula;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void setTipoCadastro(TipoCadastro tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
    }

    public void setCadastroAprovado(Boolean cadastroAprovado) {
        this.cadastroAprovado = cadastroAprovado;
    }

    public void setBatizado(Boolean batizado) {
        this.batizado = batizado;
    }

    public void setMembroDesde(LocalDate membroDesde) {
        this.membroDesde = membroDesde;
    }

    public void setVoluntario(Boolean voluntario) {
        this.voluntario = voluntario;
    }

    public void setCelula(CelulaResumoResponse celula) {
        this.celula = celula;
    }
}