package com.igreja360.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class MembroRequest {

    private String nome;
    private String email;
    private String telefone;

    private LocalDate dataNascimento;
    private String sexo;
    private String estadoCivil;
    private String endereco;
    private String instagram;
    private String tipoCadastro;

    private Boolean cadastroAprovado;

    // Dados administrativos
    private Boolean batizado;
    private LocalDate dataBatismo;
    private LocalDate membroDesde;
    private Boolean voluntario;
    private Long celulaId;

    // Dados ministeriais
    private String ministeriosVoluntario;
    private Boolean liderCelula;
    private Boolean liderMinisterio;

    // Dados familiares
    private Long paiId;
    private Long maeId;
    private Long conjugeId;
    private List<Long> filhosIds;

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

    public Long getCelulaId() {
        return celulaId;
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

    public Long getPaiId() {
        return paiId;
    }

    public Long getMaeId() {
        return maeId;
    }

    public Long getConjugeId() {
        return conjugeId;
    }

    public List<Long> getFilhosIds() {
        return filhosIds;
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

    public void setCelulaId(Long celulaId) {
        this.celulaId = celulaId;
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

    public void setPaiId(Long paiId) {
        this.paiId = paiId;
    }

    public void setMaeId(Long maeId) {
        this.maeId = maeId;
    }

    public void setConjugeId(Long conjugeId) {
        this.conjugeId = conjugeId;
    }

    public void setFilhosIds(List<Long> filhosIds) {
        this.filhosIds = filhosIds;
    }
}