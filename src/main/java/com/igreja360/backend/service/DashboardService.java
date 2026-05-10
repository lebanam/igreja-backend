package com.igreja360.backend.service;

import com.igreja360.backend.dto.DashboardResponse;
import com.igreja360.backend.model.LancamentoFinanceiro;
import com.igreja360.backend.repository.CelulaRepository;
import com.igreja360.backend.repository.LancamentoFinanceiroRepository;
import com.igreja360.backend.repository.MembroRepository;
import com.igreja360.backend.repository.MinisterioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

        LocalDate inicioMes = LocalDate.now().withDayOfMonth(1);
        LocalDate fimMes = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        List<LancamentoFinanceiro> lancamentos =
                lancamentoRepository.findByDataBetween(inicioMes, fimMes);

        BigDecimal entradas = BigDecimal.ZERO;
        BigDecimal saidas = BigDecimal.ZERO;

        for (LancamentoFinanceiro lancamento : lancamentos) {

            if ("RECEITA".equalsIgnoreCase(lancamento.getTipo())) {
                entradas = entradas.add(lancamento.getValor());
            } else {
                saidas = saidas.add(lancamento.getValor());
            }
        }

        BigDecimal saldo = entradas.subtract(saidas);

        return new DashboardResponse(
                totalMembros,
                totalCelulas,
                totalMinisterios,
                entradas,
                saidas,
                saldo
        );
    }
}