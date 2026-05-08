package com.igreja360.backend.dto;

public class IgrejaResponse {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String pastorResponsavel;
    private String logoUrl;
    private String endereco;

    public IgrejaResponse(
            Long id,
            String nome,
            String email,
            String telefone,
            String pastorResponsavel,
            String logoUrl,
            String endereco
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.pastorResponsavel = pastorResponsavel;
        this.logoUrl = logoUrl;
        this.endereco = endereco;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getPastorResponsavel() { return pastorResponsavel; }
    public String getLogoUrl() { return logoUrl; }
    public String getEndereco() { return endereco; }
}