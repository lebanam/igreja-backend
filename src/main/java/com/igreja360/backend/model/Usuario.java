package com.igreja360.backend.model;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean ativo;

    private String avatarUrl;

    private Boolean primeiroAcesso;

    @OneToOne
    @JoinColumn(name = "membro_id")
    private Membro membro;

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public Role getRole() { return role; }
    public Boolean getAtivo() { return ativo; }
    public String getAvatarUrl() { return avatarUrl; }
    public Boolean getPrimeiroAcesso() { return primeiroAcesso; }
    public Membro getMembro() { return membro; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setRole(Role role) { this.role = role; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    public void setPrimeiroAcesso(Boolean primeiroAcesso) { this.primeiroAcesso = primeiroAcesso; }
    public void setMembro(Membro membro) { this.membro = membro; }
}