package com.dgsspa.comicverse.service;

import com.dgsspa.comicverse.config.ErrorMessagesProperties;
import com.dgsspa.comicverse.config.SearchMessagesProperties;
import com.dgsspa.comicverse.config.SuccessMessagesProperties;
import com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.dto.SearchResponseDTO;
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
    private final SearchMessagesProperties searchMessagesProperties;
    private final ErrorMessagesProperties errorMessagesProperties;
    private final SuccessMessagesProperties successMessagesProperties;

    @Autowired
    public FumettoService(
            FumettoRepository fumettoRepository,
            FumettoMapper fumettoMapper,
            SearchMessagesProperties searchMessagesProperties,
            ErrorMessagesProperties errorMessagesProperties,
            SuccessMessagesProperties successMessagesProperties) {
        this.fumettoRepository = fumettoRepository;
        this.fumettoMapper = fumettoMapper;
        this.searchMessagesProperties = searchMessagesProperties;
        this.errorMessagesProperties = errorMessagesProperties;
        this.successMessagesProperties = successMessagesProperties;
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
                .orElseThrow(() -> new ResourceNotFoundException(String.format(errorMessagesProperties.getFumettoId(), id)));
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
                .orElseThrow(() -> new ResourceNotFoundException(String.format(errorMessagesProperties.getFumettoId(), id)));
    }

    @Transactional
    public String eliminaFumetto(Integer id) {
        log.debug("Eliminazione fumetto con id={}", id);
        if (!fumettoRepository.deleteById(id)) {
            throw new ResourceNotFoundException(String.format(errorMessagesProperties.getFumettoId(), id));
        }
        return String.format(successMessagesProperties.getDeleted(), "Fumetto", id);
    }

    public List<FumettoDTO> cercaPerTitolo(String titolo) {
        log.debug("Ricerca fumetti per titolo={}", titolo);
        return fumettoRepository.findAll().stream()
                .filter(fumetto -> fumetto.getTitolo().toLowerCase().contains(titolo.toLowerCase()))
                .map(fumettoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SearchResponseDTO<FumettoDTO> cercaPerTitoloConEsito(String titolo) {
        log.debug("Ricerca fumetti per titolo={}", titolo);
        List<FumettoDTO> risultati = cercaPerTitolo(titolo);
        return buildSearchResponse(
                risultati,
                String.format(searchMessagesProperties.getNoResults(), titolo)
        );
    }

    public SearchResponseDTO<FumettoDTO> cercaPubblicatiDopoConEsito(LocalDateTime data) {
        log.debug("Ricerca fumetti pubblicati dopo={}", data);
        List<FumettoDTO> risultati = fumettoRepository.findByDataPubblicazioneAfter(data).stream()
                .map(fumettoMapper::toDTO)
                .collect(Collectors.toList());
        return buildSearchResponse(
                risultati,
                String.format(searchMessagesProperties.getNoResults(), data)
        );
    }

    private SearchResponseDTO<FumettoDTO> buildSearchResponse(List<FumettoDTO> risultati, String emptyMessage) {
        if (risultati.isEmpty()) {
            return new SearchResponseDTO<>(risultati, 0, emptyMessage);
        }
        return new SearchResponseDTO<>(risultati, risultati.size(), null);
    }
}
