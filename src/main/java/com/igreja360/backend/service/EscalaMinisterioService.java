package com.igreja360.backend.service;

import com.igreja360.backend.dto.EscalaMinisterioRequest;
import com.igreja360.backend.dto.EscalaMinisterioResponse;
import com.igreja360.backend.dto.EscalaParticipanteRequest;
import com.igreja360.backend.model.EscalaMinisterio;
import com.igreja360.backend.model.EscalaParticipante;
import com.igreja360.backend.model.Membro;
import com.igreja360.backend.model.Ministerio;
import com.igreja360.backend.repository.EscalaMinisterioRepository;
import com.igreja360.backend.repository.MembroRepository;
import com.igreja360.backend.repository.MinisterioRepository;
import com.igreja360.backend.dto.EscalaParticipanteResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EscalaMinisterioService {

    private final EscalaMinisterioRepository escalaRepository;
    private final MinisterioRepository ministerioRepository;
    private final MembroRepository membroRepository;

    public EscalaMinisterioService(
            EscalaMinisterioRepository escalaRepository,
            MinisterioRepository ministerioRepository,
            MembroRepository membroRepository
    ) {
        this.escalaRepository = escalaRepository;
        this.ministerioRepository = ministerioRepository;
        this.membroRepository = membroRepository;
    }

    public List<EscalaMinisterioResponse> listarPorMinisterio(Long ministerioId) {
        return escalaRepository.findByMinisterio_IdOrderByDataAscHorarioAsc(ministerioId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EscalaMinisterioResponse buscarPorId(Long id) {
        EscalaMinisterio escala = buscarEntidadePorId(id);
        return toResponseDTO(escala);
    }

    public EscalaMinisterioResponse criar(EscalaMinisterioRequest requestDTO) {
        Ministerio ministerio = buscarMinisterioPorId(requestDTO.getMinisterioId());

        EscalaMinisterio escala = new EscalaMinisterio();
        escala.setData(requestDTO.getData());
        escala.setHorario(requestDTO.getHorario());
        escala.setTitulo(requestDTO.getTitulo());
        escala.setTextoEscala(requestDTO.getTextoEscala());
        escala.setObservacoes(requestDTO.getObservacoes());
        escala.setMinisterio(ministerio);

        adicionarParticipantes(escala, requestDTO.getParticipantes());

        EscalaMinisterio salva = escalaRepository.save(escala);

        return toResponseDTO(salva);
    }

    public EscalaMinisterioResponse atualizar(Long id, EscalaMinisterioRequest requestDTO) {
        EscalaMinisterio escala = buscarEntidadePorId(id);
        Ministerio ministerio = buscarMinisterioPorId(requestDTO.getMinisterioId());

        escala.setData(requestDTO.getData());
        escala.setHorario(requestDTO.getHorario());
        escala.setTitulo(requestDTO.getTitulo());
        escala.setTextoEscala(requestDTO.getTextoEscala());
        escala.setObservacoes(requestDTO.getObservacoes());
        escala.setMinisterio(ministerio);

        escala.getParticipantes().clear();
        adicionarParticipantes(escala, requestDTO.getParticipantes());

        EscalaMinisterio atualizada = escalaRepository.save(escala);

        return toResponseDTO(atualizada);
    }

    public void deletar(Long id) {
        EscalaMinisterio escala = buscarEntidadePorId(id);
        escalaRepository.delete(escala);
    }

    private void adicionarParticipantes(
            EscalaMinisterio escala,
            List<EscalaParticipanteRequest> participantesRequest
    ) {
        if (participantesRequest == null || participantesRequest.isEmpty()) {
            return;
        }

        for (EscalaParticipanteRequest participanteRequest : participantesRequest) {
            if (participanteRequest.getFuncao() == null || participanteRequest.getMembroId() == null) {
                continue;
            }

            Membro membro = buscarMembroPorId(participanteRequest.getMembroId());

            EscalaParticipante participante = new EscalaParticipante();
            participante.setFuncao(participanteRequest.getFuncao());
            participante.setMembro(membro);
            participante.setEscalaMinisterio(escala);

            escala.getParticipantes().add(participante);
        }
    }

    private EscalaMinisterio buscarEntidadePorId(Long id) {
        return escalaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Escala não encontrada com ID: " + id));
    }

    private Ministerio buscarMinisterioPorId(Long id) {
        return ministerioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ministério não encontrado com ID: " + id));
    }

    private Membro buscarMembroPorId(Long id) {
        return membroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado com ID: " + id));
    }

    private EscalaMinisterioResponse toResponseDTO(EscalaMinisterio escala) {

        List<EscalaParticipanteResponse> participantes = escala.getParticipantes()
                .stream()
                .map(p -> new EscalaParticipanteResponse(
                        p.getFuncao(),
                        p.getMembro().getId(),
                        p.getMembro().getNome()
                ))
                .toList();

        return new EscalaMinisterioResponse(
                escala.getId(),
                escala.getData(),
                escala.getHorario(),
                escala.getTitulo(),
                escala.getTextoEscala(),
                escala.getObservacoes(),
                escala.getMinisterio().getId(),
                escala.getMinisterio().getNome(),
                participantes
        );
    }
}