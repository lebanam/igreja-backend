package com.igreja360.backend.service;

import com.igreja360.backend.dto.FinanceiroRequest;
import com.igreja360.backend.dto.FinanceiroResponse;
import com.igreja360.backend.model.LancamentoFinanceiro;
import com.igreja360.backend.repository.LancamentoFinanceiroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceiroService {

    private final LancamentoFinanceiroRepository financeiroRepository;

    public FinanceiroService(LancamentoFinanceiroRepository financeiroRepository) {
        this.financeiroRepository = financeiroRepository;
    }

    public List<FinanceiroResponse> listarTodos() {
        return financeiroRepository.findAllByOrderByDataDesc()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public FinanceiroResponse criar(FinanceiroRequest requestDTO) {
        LancamentoFinanceiro lancamento = new LancamentoFinanceiro();
        lancamento.setTipo(requestDTO.getTipo());
        lancamento.setDescricao(requestDTO.getDescricao());
        lancamento.setValor(requestDTO.getValor());
        lancamento.setData(requestDTO.getData());
        lancamento.setCategoria(requestDTO.getCategoria());
        lancamento.setObservacoes(requestDTO.getObservacoes());

        LancamentoFinanceiro salvo = financeiroRepository.save(lancamento);

        return toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        LancamentoFinanceiro lancamento = financeiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado com ID: " + id));

        financeiroRepository.delete(lancamento);
    }

    private FinanceiroResponse toResponseDTO(LancamentoFinanceiro lancamento) {
        return new FinanceiroResponse(
                lancamento.getId(),
                lancamento.getTipo(),
                lancamento.getDescricao(),
                lancamento.getValor(),
                lancamento.getData(),
                lancamento.getCategoria(),
                lancamento.getObservacoes()
        );
    }
}