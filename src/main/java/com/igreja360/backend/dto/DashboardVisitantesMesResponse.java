package com.igreja360.backend.dto;

public class DashboardVisitantesMesResponse {

    private String mes;
    private Integer quantidade;

    public DashboardVisitantesMesResponse(String mes, Integer quantidade) {
        this.mes = mes;
        this.quantidade = quantidade;
    }

    public String getMes() { return mes; }
    public Integer getQuantidade() { return quantidade; }
}