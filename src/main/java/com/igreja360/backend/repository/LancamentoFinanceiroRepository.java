package com.igreja360.backend.repository;

import com.igreja360.backend.model.LancamentoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LancamentoFinanceiroRepository
        extends JpaRepository<LancamentoFinanceiro, Long> {

    List<LancamentoFinanceiro> findAllByOrderByDataDesc();

    List<LancamentoFinanceiro> findByDataBetween(
            LocalDate inicio,
            LocalDate fim
    );
}