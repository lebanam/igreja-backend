package com.igreja360.backend.dto;

public class DashboardMembrosCelulaResponse {

    private Long comCelula;
    private Long semCelula;

    public DashboardMembrosCelulaResponse(Long comCelula, Long semCelula) {
        this.comCelula = comCelula;
        this.semCelula = semCelula;
    }

    public Long getComCelula() { return comCelula; }
    public Long getSemCelula() { return semCelula; }
}