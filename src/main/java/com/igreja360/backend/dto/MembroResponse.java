package com.igreja360.backend.dto;

import com.igreja360.backend.model.TipoCadastro;

import java.time.LocalDate;
import java.util.List;

public class MembroResponse {

    private Long id;

    private String nome;

    private String email;


    private String telefone;

    private LocalDate dataNascimento;

    private Integer idade;

    private String sexo;

    private String estadoCivil;

    private String endereco;

    private String instagram;

    private TipoCadastro tipoCadastro;

    private Boolean cadastroAprovado;

    // Dados administrativos
    private Boolean batizado;

    private LocalDate dataBatismo;

    private LocalDate membroDesde;

    private Boolean voluntario;

    private String ministeriosVoluntario;

    private Boolean liderCelula;

    private Boolean liderMinisterio;

    private CelulaResumoResponse celula;

    // Família
    private MembroResumoResponse pai;

    private MembroResumoResponse mae;

    private MembroResumoResponse conjuge;

    private List<MembroResumoResponse> filhos;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
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

    public LocalDate getDataBatismo() {
        return dataBatismo;
    }

    public LocalDate getMembroDesde() {
        return membroDesde;
    }

    public Boolean getVoluntario() {
        return voluntario;
    }

    public String getMinisteriosVoluntario() {
        return ministeriosVoluntario;
    }

    public Boolean getLiderCelula() {
        return liderCelula;
    }

    public Boolean getLiderMinisterio() {
        return liderMinisterio;
    }

    public CelulaResumoResponse getCelula() {
        return celula;
    }

    public MembroResumoResponse getPai() {
        return pai;
    }

    public MembroResumoResponse getMae() {
        return mae;
    }

    public MembroResumoResponse getConjuge() {
        return conjuge;
    }

    public List<MembroResumoResponse> getFilhos() {
        return filhos;
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

    public void setDataBatismo(LocalDate dataBatismo) {
        this.dataBatismo = dataBatismo;
    }

    public void setMembroDesde(LocalDate membroDesde) {
        this.membroDesde = membroDesde;
    }

    public void setVoluntario(Boolean voluntario) {
        this.voluntario = voluntario;
    }

    public void setMinisteriosVoluntario(String ministeriosVoluntario) {
        this.ministeriosVoluntario = ministeriosVoluntario;
    }

    public void setLiderCelula(Boolean liderCelula) {
        this.liderCelula = liderCelula;
    }

    public void setLiderMinisterio(Boolean liderMinisterio) {
        this.liderMinisterio = liderMinisterio;
    }

    public void setCelula(CelulaResumoResponse celula) {
        this.celula = celula;
    }

    public void setPai(MembroResumoResponse pai) {
        this.pai = pai;
    }

    public void setMae(MembroResumoResponse mae) {
        this.mae = mae;
    }

    public void setConjuge(MembroResumoResponse conjuge) {
        this.conjuge = conjuge;
    }

    public void setFilhos(List<MembroResumoResponse> filhos) {
        this.filhos = filhos;
    }
}