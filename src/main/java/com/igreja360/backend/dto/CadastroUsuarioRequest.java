package com.igreja360.backend.dto;

import java.time.LocalDate;

public class CadastroUsuarioRequest {

    private String nome;

    private String telefone;

    private String email;

    private LocalDate dataNascimento;

    private String sexo;

    private String estadoCivil;

    private String endereco;

    private String instagram;

    private String tipoCadastro;

    private String senha;

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
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

    public String getTipoCadastro() {
        return tipoCadastro;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public void setTipoCadastro(String tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}