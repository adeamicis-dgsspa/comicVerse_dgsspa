package com.dgsspa.comicverse.service;

import com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.mapper.FumettoMapper;
import com.dgsspa.comicverse.model.Fumetto;
import com.dgsspa.comicverse.repository.FumettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class FumettoService {

    private final FumettoRepository fumettoRepository;
    private final FumettoMapper fumettoMapper;

    @Autowired
    public FumettoService(FumettoRepository fumettoRepository, FumettoMapper fumettoMapper) {
        this.fumettoRepository = fumettoRepository;
        this.fumettoMapper = fumettoMapper;
    }

    public List<FumettoDTO> stampaTuttiFumetti() {
        return fumettoRepository.findAll().stream()
                .map(fumettoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FumettoDTO recuperaFumettoPerId(Integer id) {
        return fumettoRepository.findById(id)
                .map(fumettoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Fumetto non trovato con id: " + id));
    }

    @Transactional
    public FumettoDTO inserisciNuovoFumetto(FumettoDTO fumettoDTO) {
        Fumetto fumetto = fumettoMapper.toEntity(fumettoDTO);
        Fumetto saved = fumettoRepository.save(fumetto);
        return fumettoMapper.toDTO(saved);
    }

    @Transactional
    public FumettoDTO aggiornaFumetto(Integer id, FumettoDTO fumettoDTO) {
        return fumettoRepository.findById(id)
                .map(existing -> {
                    fumettoMapper.updateEntityFromDTO(fumettoDTO, existing);
                    Fumetto updated = fumettoRepository.save(existing);
                    return fumettoMapper.toDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Fumetto non trovato con id: " + id));
    }

    @Transactional
    public void eliminaFumetto(Integer id) {
        if (!fumettoRepository.existsById(id)) {
            throw new RuntimeException("Fumetto non trovato con id: " + id);
        }
        fumettoRepository.deleteById(id);
    }

    public boolean esisteFumetto(Integer id) {
        return fumettoRepository.existsById(id);
    }

    public List<FumettoDTO> cercaPerTitolo(String titolo) {
        return fumettoRepository.findAll().stream()
                .filter(fumetto -> fumetto.getTitolo().toLowerCase().contains(titolo.toLowerCase()))
                .map(fumettoMapper::toDTO)
                .collect(Collectors.toList());
    }
}