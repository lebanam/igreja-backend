package com.igreja360.backend.dto;

public class IgrejaRequest {

    private String nome;
    private String email;
    private String telefone;
    private String pastorResponsavel;
    private String logoUrl;
    private String endereco;

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getPastorResponsavel() { return pastorResponsavel; }
    public String getLogoUrl() { return logoUrl; }
    public String getEndereco() { return endereco; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setPastorResponsavel(String pastorResponsavel) { this.pastorResponsavel = pastorResponsavel; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}