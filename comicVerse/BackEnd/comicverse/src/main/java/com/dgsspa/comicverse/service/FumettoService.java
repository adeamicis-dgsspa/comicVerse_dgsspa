package com.dgsspa.comicverse.service;

import com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.exception.ResourceNotFoundException;
import com.dgsspa.comicverse.mapper.FumettoMapper;
import com.dgsspa.comicverse.model.Fumetto;
import com.dgsspa.comicverse.repository.FumettoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class FumettoService {

    private static final Logger log = LoggerFactory.getLogger(FumettoService.class);

    private final FumettoRepository fumettoRepository;
    private final FumettoMapper fumettoMapper;

    @Autowired
    public FumettoService(FumettoRepository fumettoRepository, FumettoMapper fumettoMapper) {
        this.fumettoRepository = fumettoRepository;
        this.fumettoMapper = fumettoMapper;
    }

    public List<FumettoDTO> stampaTuttiFumetti() {
        log.debug("Recupero di tutti i fumetti");
        return fumettoRepository.findAll().stream()
                .map(fumettoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FumettoDTO recuperaFumettoPerId(Integer id) {
        log.debug("Recupero fumetto con id={}", id);
        return fumettoRepository.findById(id)
                .map(fumettoMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Fumetto non trovato con id: " + id));
    }

    @Transactional
    public FumettoDTO inserisciNuovoFumetto(FumettoDTO fumettoDTO) {
        log.debug("Inserimento nuovo fumetto: titolo={}", fumettoDTO.getTitolo());
        Fumetto fumetto = fumettoMapper.toEntity(fumettoDTO);
        Fumetto saved = fumettoRepository.save(fumetto);
        log.info("Nuovo fumetto inserito con id={}", saved.getId());
        return fumettoMapper.toDTO(saved);
    }

    @Transactional
    public FumettoDTO aggiornaFumetto(Integer id, FumettoDTO fumettoDTO) {
        log.debug("Aggiornamento fumetto con id={}", id);
        return fumettoRepository.findById(id)
                .map(existing -> {
                    fumettoMapper.updateEntityFromDTO(fumettoDTO, existing);
                    Fumetto updated = fumettoRepository.save(existing);
                    log.info("Fumetto aggiornato con id={}", updated.getId());
                    return fumettoMapper.toDTO(updated);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Fumetto non trovato con id: " + id));
    }

    @Transactional
    public void eliminaFumetto(Integer id) {
        log.debug("Eliminazione fumetto con id={}", id);
        if (!fumettoRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException("Fumetto non trovato con id: " + id);
        }
        fumettoRepository.deleteById(id);
    }

    public List<FumettoDTO> cercaPerTitolo(String titolo) {
        log.debug("Ricerca fumetti per titolo={}", titolo);
        return fumettoRepository.findAll().stream()
                .filter(fumetto -> fumetto.getTitolo().toLowerCase().contains(titolo.toLowerCase()))
                .map(fumettoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<FumettoDTO> cercaPerTitoloCheIniziaCon(String prefisso) {
        log.debug("Ricerca fumetti con titolo che inizia con={}", prefisso);
        return fumettoRepository.findByTitoloStartingWith(prefisso).stream()
                .map(fumettoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<FumettoDTO> cercaPubblicatiDopo(LocalDateTime data) {
        log.debug("Ricerca fumetti pubblicati dopo={}", data);
        return fumettoRepository.findByDataPubblicazioneAfter(data).stream()
                .map(fumettoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
