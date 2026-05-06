package com.igreja360.backend.service;

import com.igreja360.backend.dto.*;
import com.igreja360.backend.model.Celula;
import com.igreja360.backend.model.Membro;
import com.igreja360.backend.model.PresencaCelula;
import com.igreja360.backend.model.RelatorioCelula;
import com.igreja360.backend.repository.CelulaRepository;
import com.igreja360.backend.repository.MembroRepository;
import com.igreja360.backend.repository.RelatorioCelulaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioCelulaService {

    private final RelatorioCelulaRepository relatorioRepository;
    private final CelulaRepository celulaRepository;
    private final MembroRepository membroRepository;

    public RelatorioCelulaService(
            RelatorioCelulaRepository relatorioRepository,
            CelulaRepository celulaRepository,
            MembroRepository membroRepository
    ) {
        this.relatorioRepository = relatorioRepository;
        this.celulaRepository = celulaRepository;
        this.membroRepository = membroRepository;
    }

    public RelatorioCelulaResponse criar(Long celulaId, RelatorioCelulaRequest request) {
        Celula celula = celulaRepository.findById(celulaId)
                .orElseThrow(() -> new RuntimeException("Célula não encontrada"));

        if (request.getDataEncontro() == null) {
            throw new RuntimeException("Informe a data do encontro");
        }

        RelatorioCelula relatorio = new RelatorioCelula();
        relatorio.setCelula(celula);
        relatorio.setDataEncontro(request.getDataEncontro());
        relatorio.setTema(request.getTema());
        relatorio.setVisitantes(request.getVisitantes() == null ? 0 : request.getVisitantes());
        relatorio.setObservacoes(request.getObservacoes());

        List<PresencaCelula> presencas = new ArrayList<>();

        if (request.getPresencas() != null) {
            for (PresencaCelulaRequest presencaRequest : request.getPresencas()) {
                Membro membro = membroRepository.findById(presencaRequest.getMembroId())
                        .orElseThrow(() -> new RuntimeException("Membro não encontrado"));

                PresencaCelula presenca = new PresencaCelula();
                presenca.setRelatorio(relatorio);
                presenca.setMembro(membro);
                presenca.setPresente(Boolean.TRUE.equals(presencaRequest.getPresente()));

                presencas.add(presenca);
            }
        }

        relatorio.setPresencas(presencas);

        RelatorioCelula salvo = relatorioRepository.save(relatorio);
        return converterParaResponse(salvo);
    }

    public List<RelatorioCelulaResponse> listarPorCelula(Long celulaId) {
        return relatorioRepository.findByCelulaIdOrderByDataEncontroDesc(celulaId)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public RelatorioCelulaResponse buscarPorId(Long id) {
        RelatorioCelula relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado"));

        return converterParaResponse(relatorio);
    }

    public void excluir(Long id) {
        if (!relatorioRepository.existsById(id)) {
            throw new RuntimeException("Relatório não encontrado");
        }

        relatorioRepository.deleteById(id);
    }

    private RelatorioCelulaResponse converterParaResponse(RelatorioCelula relatorio) {
        List<PresencaCelulaResponse> presencas = relatorio.getPresencas() == null
                ? List.of()
                : relatorio.getPresencas()
                  .stream()
                  .map(p -> new PresencaCelulaResponse(
                          p.getId(),
                          p.getMembro().getId(),
                          p.getMembro().getNome(),
                          p.getPresente()
                  ))
                  .toList();

        int totalPresentes = (int) presencas.stream()
                .filter(PresencaCelulaResponse::getPresente)
                .count();

        int totalAusentes = presencas.size() - totalPresentes;

        return new RelatorioCelulaResponse(
                relatorio.getId(),
                relatorio.getDataEncontro(),
                relatorio.getTema(),
                relatorio.getVisitantes(),
                relatorio.getObservacoes(),
                relatorio.getCriadoEm(),
                relatorio.getCelula().getId(),
                relatorio.getCelula().getNome(),
                totalPresentes,
                totalAusentes,
                presencas
        );
    }
}