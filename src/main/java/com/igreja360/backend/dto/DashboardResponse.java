package com.igreja360.backend.dto;

import java.math.BigDecimal;

public class DashboardResponse {

    private Long totalMembros;
    private Long totalCelulas;
    private Long totalMinisterios;

    private BigDecimal entradasMes;
    private BigDecimal saidasMes;
    private BigDecimal saldoMes;

    public DashboardResponse(
            Long totalMembros,
            Long totalCelulas,
            Long totalMinisterios,
            BigDecimal entradasMes,
            BigDecimal saidasMes,
            BigDecimal saldoMes
    ) {
        this.totalMembros = totalMembros;
        this.totalCelulas = totalCelulas;
        this.totalMinisterios = totalMinisterios;
        this.entradasMes = entradasMes;
        this.saidasMes = saidasMes;
        this.saldoMes = saldoMes;
    }

    public Long getTotalMembros() {
        return totalMembros;
    }

    public Long getTotalCelulas() {
        return totalCelulas;
    }

    public Long getTotalMinisterios() {
        return totalMinisterios;
    }

    public BigDecimal getEntradasMes() {
        return entradasMes;
    }

    public BigDecimal getSaidasMes() {
        return saidasMes;
    }

    public BigDecimal getSaldoMes() {
        return saldoMes;
    }
}