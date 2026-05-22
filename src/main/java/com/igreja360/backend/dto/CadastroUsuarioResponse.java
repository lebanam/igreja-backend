package com.igreja360.backend.dto;

public class CadastroUsuarioResponse {

    private String mensagem;
    private String nome;
    private String email;
    private String role;

    public CadastroUsuarioResponse(
            String mensagem,
            String nome,
            String email,
            String role
    ) {
        this.mensagem = mensagem;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}