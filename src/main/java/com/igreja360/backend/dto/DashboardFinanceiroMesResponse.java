package com.igreja360.backend.dto;

import java.math.BigDecimal;

public class DashboardFinanceiroMesResponse {

    private String mes;
    private BigDecimal entradas;
    private BigDecimal saidas;
    private BigDecimal saldo;

    public DashboardFinanceiroMesResponse(
            String mes,
            BigDecimal entradas,
            BigDecimal saidas,
            BigDecimal saldo
    ) {
        this.mes = mes;
        this.entradas = entradas;
        this.saidas = saidas;
        this.saldo = saldo;
    }

    public String getMes() { return mes; }
    public BigDecimal getEntradas() { return entradas; }
    public BigDecimal getSaidas() { return saidas; }
    public BigDecimal getSaldo() { return saldo; }
}