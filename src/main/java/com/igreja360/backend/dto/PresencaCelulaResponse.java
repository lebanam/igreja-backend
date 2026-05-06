package com.igreja360.backend.dto;

public class PresencaCelulaResponse {

    private Long id;
    private Long membroId;
    private String nomeMembro;
    private Boolean presente;

    public PresencaCelulaResponse(Long id, Long membroId, String nomeMembro, Boolean presente) {
        this.id = id;
        this.membroId = membroId;
        this.nomeMembro = nomeMembro;
        this.presente = presente;
    }

    public Long getId() { return id; }
    public Long getMembroId() { return membroId; }
    public String getNomeMembro() { return nomeMembro; }
    public Boolean getPresente() { return presente; }
}