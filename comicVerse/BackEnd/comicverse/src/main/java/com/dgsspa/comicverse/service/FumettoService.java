package com.dgsspa.comicverse.service;

import com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.model.Fumetto;
import com.dgsspa.comicverse.repository.FumettoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FumettoService {

    private final FumettoRepository fumettoRepository;

    public FumettoService(FumettoRepository fumettoRepository) {
        this.fumettoRepository = fumettoRepository;
    }

    @Transactional(readOnly = true)
    public List<FumettoDTO> stampaTuttiFumetti() {
        List<Fumetto> fumetti = fumettoRepository.findAll();
        List<FumettoDTO> dtos = new ArrayList<>();

        for (Fumetto fumetto : fumetti) {
            dtos.add(convertToDTO(fumetto));
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public FumettoDTO recuperaFumettoPerId(Integer id) {
        return fumettoRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Fumetto non trovato con id: " + id));
    }

    public FumettoDTO inserisciNuovoFumetto(FumettoDTO fumettoDTO) {
        Fumetto fumetto = convertToEntity(fumettoDTO);
        Fumetto saved = fumettoRepository.save(fumetto);
        return convertToDTO(saved);
    }

    public FumettoDTO aggiornaFumetto(Integer id, FumettoDTO fumettoDTO) {
        return fumettoRepository.findById(id)
                .map(existing -> {
                    updateEntityFromDTO(existing, fumettoDTO);
                    Fumetto updated = fumettoRepository.save(existing);
                    return convertToDTO(updated);
                })
                .orElseThrow(() -> new RuntimeException("Fumetto non trovato con id: " + id));
    }

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
        List<Fumetto> fumetti = fumettoRepository.findAll();
        List<FumettoDTO> risultati = new ArrayList<>();

        for (Fumetto fumetto : fumetti) {
            if (fumetto.getTitolo().toLowerCase().contains(titolo.toLowerCase())) {
                risultati.add(convertToDTO(fumetto));
            }
        }

        return risultati;
    }

    private FumettoDTO convertToDTO(Fumetto fumetto) {
        FumettoDTO dto = new FumettoDTO();
        dto.setId(fumetto.getId());
        dto.setTitolo(fumetto.getTitolo());
        dto.setEditore(fumetto.getEditore());
        dto.setVolume(fumetto.getVolume());
        dto.setDataPubblicazione(fumetto.getDataPubblicazione());
        dto.setDescrizione(fumetto.getDescrizione());
        return dto;
    }

    private Fumetto convertToEntity(FumettoDTO dto) {
        Fumetto fumetto = new Fumetto();
        fumetto.setTitolo(dto.getTitolo());
        fumetto.setEditore(dto.getEditore());
        fumetto.setVolume(dto.getVolume());
        fumetto.setDataPubblicazione(dto.getDataPubblicazione());
        fumetto.setDescrizione(dto.getDescrizione());
        return fumetto;
    }

    private void updateEntityFromDTO(Fumetto entity, FumettoDTO dto) {
        entity.setTitolo(dto.getTitolo());
        entity.setEditore(dto.getEditore());
        entity.setVolume(dto.getVolume());
        entity.setDataPubblicazione(dto.getDataPubblicazione());
        entity.setDescrizione(dto.getDescrizione());
    }
}