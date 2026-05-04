package com.igreja360.backend.dto;

public class EscalaParticipanteResponse {

    private String funcao;
    private Long membroId;
    private String membroNome;

    public EscalaParticipanteResponse() {
    }

    public EscalaParticipanteResponse(String funcao, Long membroId, String membroNome) {
        this.funcao = funcao;
        this.membroId = membroId;
        this.membroNome = membroNome;
    }

    public String getFuncao() {
        return funcao;
    }

    public Long getMembroId() {
        return membroId;
    }

    public String getMembroNome() {
        return membroNome;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setMembroId(Long membroId) {
        this.membroId = membroId;
    }

    public void setMembroNome(String membroNome) {
        this.membroNome = membroNome;
    }
}