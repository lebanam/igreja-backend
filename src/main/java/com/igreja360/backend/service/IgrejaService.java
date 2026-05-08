package com.igreja360.backend.service;

import com.igreja360.backend.dto.IgrejaRequest;
import com.igreja360.backend.dto.IgrejaResponse;
import com.igreja360.backend.model.Igreja;
import com.igreja360.backend.repository.IgrejaRepository;
import org.springframework.stereotype.Service;

@Service
public class IgrejaService {

    private final IgrejaRepository igrejaRepository;

    public IgrejaService(IgrejaRepository igrejaRepository) {
        this.igrejaRepository = igrejaRepository;
    }

    public IgrejaResponse buscarConfiguracao() {
        Igreja igreja = obterOuCriarIgreja();
        return converterParaResponse(igreja);
    }

    public IgrejaResponse atualizarConfiguracao(IgrejaRequest request) {
        Igreja igreja = obterOuCriarIgreja();

        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new RuntimeException("Informe o nome da igreja");
        }

        igreja.setNome(request.getNome().trim());
        igreja.setEmail(request.getEmail());
        igreja.setTelefone(request.getTelefone());
        igreja.setPastorResponsavel(request.getPastorResponsavel());
        igreja.setLogoUrl(request.getLogoUrl());
        igreja.setEndereco(request.getEndereco());

        Igreja salva = igrejaRepository.save(igreja);
        return converterParaResponse(salva);
    }

    private Igreja obterOuCriarIgreja() {
        return igrejaRepository.findAll()
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    Igreja igreja = new Igreja();
                    igreja.setNome("Igreja360");
                    igreja.setEmail("");
                    igreja.setTelefone("");
                    igreja.setPastorResponsavel("");
                    igreja.setLogoUrl("");
                    igreja.setEndereco("");
                    return igrejaRepository.save(igreja);
                });
    }

    private IgrejaResponse converterParaResponse(Igreja igreja) {
        return new IgrejaResponse(
                igreja.getId(),
                igreja.getNome(),
                igreja.getEmail(),
                igreja.getTelefone(),
                igreja.getPastorResponsavel(),
                igreja.getLogoUrl(),
                igreja.getEndereco()
        );
    }
}