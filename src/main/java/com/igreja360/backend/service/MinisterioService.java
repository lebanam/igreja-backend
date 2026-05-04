package com.igreja360.backend.service;

import com.igreja360.backend.dto.MinisterioRequest;
import com.igreja360.backend.dto.MinisterioResponse;
import com.igreja360.backend.model.Ministerio;
import com.igreja360.backend.repository.MinisterioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MinisterioService {

    private final MinisterioRepository ministerioRepository;

    public MinisterioService(MinisterioRepository ministerioRepository) {
        this.ministerioRepository = ministerioRepository;
    }

    public List<MinisterioResponse> listarTodos() {
        return ministerioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public MinisterioResponse buscarPorId(Long id) {
        Ministerio ministerio = buscarEntidadePorId(id);
        return toResponseDTO(ministerio);
    }

    public MinisterioResponse criar(MinisterioRequest requestDTO) {
        Ministerio ministerio = new Ministerio();
        ministerio.setNome(requestDTO.getNome());
        ministerio.setDescricao(requestDTO.getDescricao());

        Ministerio salvo = ministerioRepository.save(ministerio);

        return toResponseDTO(salvo);
    }

    public MinisterioResponse atualizar(Long id, MinisterioRequest requestDTO) {
        Ministerio ministerio = buscarEntidadePorId(id);

        ministerio.setNome(requestDTO.getNome());
        ministerio.setDescricao(requestDTO.getDescricao());

        Ministerio atualizado = ministerioRepository.save(ministerio);

        return toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        Ministerio ministerio = buscarEntidadePorId(id);
        ministerioRepository.delete(ministerio);
    }

    private Ministerio buscarEntidadePorId(Long id) {
        return ministerioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ministério não encontrado com ID: " + id));
    }

    private MinisterioResponse toResponseDTO(Ministerio ministerio) {
        return new MinisterioResponse(
                ministerio.getId(),
                ministerio.getNome(),
                ministerio.getDescricao()
        );
    }
}