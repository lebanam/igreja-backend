package com.igreja360.backend.dto;

public class PresencaCelulaRequest {

    private Long membroId;
    private Boolean presente;

    public Long getMembroId() { return membroId; }
    public Boolean getPresente() { return presente; }

    public void setMembroId(Long membroId) { this.membroId = membroId; }
    public void setPresente(Boolean presente) { this.presente = presente; }
}