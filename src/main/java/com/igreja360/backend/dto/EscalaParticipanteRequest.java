package com.igreja360.backend.dto;

public class EscalaParticipanteRequest {

    private String funcao;
    private Long membroId;

    public String getFuncao() {
        return funcao;
    }

    public Long getMembroId() {
        return membroId;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setMembroId(Long membroId) {
        this.membroId = membroId;
    }
}