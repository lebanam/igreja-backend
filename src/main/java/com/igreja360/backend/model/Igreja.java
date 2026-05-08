package com.igreja360.backend.model;

import jakarta.persistence.*;

@Entity
public class Igreja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String pastorResponsavel;
    private String logoUrl;

    @Column(columnDefinition = "TEXT")
    private String endereco;

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getPastorResponsavel() { return pastorResponsavel; }
    public String getLogoUrl() { return logoUrl; }
    public String getEndereco() { return endereco; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setPastorResponsavel(String pastorResponsavel) { this.pastorResponsavel = pastorResponsavel; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}