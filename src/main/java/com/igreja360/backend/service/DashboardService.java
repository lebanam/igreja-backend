package com.igreja360.backend.service;

import com.igreja360.backend.dto.DashboardFinanceiroMesResponse;
import com.igreja360.backend.dto.DashboardResponse;
import com.igreja360.backend.model.LancamentoFinanceiro;
import com.igreja360.backend.repository.CelulaRepository;
import com.igreja360.backend.repository.LancamentoFinanceiroRepository;
import com.igreja360.backend.repository.MembroRepository;
import com.igreja360.backend.repository.MinisterioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class DashboardService {

    private final MembroRepository membroRepository;
    private final CelulaRepository celulaRepository;
    private final MinisterioRepository ministerioRepository;
    private final LancamentoFinanceiroRepository lancamentoRepository;

    public DashboardService(
            MembroRepository membroRepository,
            CelulaRepository celulaRepository,
            MinisterioRepository ministerioRepository,
            LancamentoFinanceiroRepository lancamentoRepository
    ) {
        this.membroRepository = membroRepository;
        this.celulaRepository = celulaRepository;
        this.ministerioRepository = ministerioRepository;
        this.lancamentoRepository = lancamentoRepository;
    }

    public DashboardResponse buscarDashboard() {
        Long totalMembros = membroRepository.count();
        Long totalCelulas = celulaRepository.count();
        Long totalMinisterios = ministerioRepository.count();

        LocalDate hoje = LocalDate.now();
        LocalDate inicioMes = hoje.withDayOfMonth(1);
        LocalDate fimMes = hoje.withDayOfMonth(hoje.lengthOfMonth());

        List<LancamentoFinanceiro> lancamentosMes =
                lancamentoRepository.findByDataBetween(inicioMes, fimMes);

        BigDecimal entradasMes = calcularTotalPorTipo(lancamentosMes, "RECEITA");
        BigDecimal saidasMes = calcularTotalPorTipo(lancamentosMes, "DESPESA");
        BigDecimal saldoMes = entradasMes.subtract(saidasMes);

        List<DashboardFinanceiroMesResponse> graficoFinanceiro =
                montarGraficoFinanceiroUltimosTresMeses();

        return new DashboardResponse(
                totalMembros,
                totalCelulas,
                totalMinisterios,
                entradasMes,
                saidasMes,
                saldoMes,
                graficoFinanceiro
        );
    }

    private List<DashboardFinanceiroMesResponse> montarGraficoFinanceiroUltimosTresMeses() {
        List<DashboardFinanceiroMesResponse> dados = new ArrayList<>();
        LocalDate hoje = LocalDate.now();

        for (int i = 2; i >= 0; i--) {
            LocalDate referencia = hoje.minusMonths(i);
            LocalDate inicio = referencia.withDayOfMonth(1);
            LocalDate fim = referencia.withDayOfMonth(referencia.lengthOfMonth());

            List<LancamentoFinanceiro> lancamentos =
                    lancamentoRepository.findByDataBetween(inicio, fim);

            BigDecimal entradas = calcularTotalPorTipo(lancamentos, "RECEITA");
            BigDecimal saidas = calcularTotalPorTipo(lancamentos, "DESPESA");
            BigDecimal saldo = entradas.subtract(saidas);

            String mes = referencia
                    .getMonth()
                    .getDisplayName(TextStyle.SHORT, new Locale("pt", "BR"))
                    .replace(".", "");

            mes = mes.substring(0, 1).toUpperCase() + mes.substring(1);

            dados.add(new DashboardFinanceiroMesResponse(
                    mes,
                    entradas,
                    saidas,
                    saldo
            ));
        }

        return dados;
    }

    private BigDecimal calcularTotalPorTipo(
            List<LancamentoFinanceiro> lancamentos,
            String tipo
    ) {
        BigDecimal total = BigDecimal.ZERO;

        for (LancamentoFinanceiro lancamento : lancamentos) {
            if (tipo.equalsIgnoreCase(lancamento.getTipo())) {
                total = total.add(lancamento.getValor());
            }
        }

        return total;
    }
}