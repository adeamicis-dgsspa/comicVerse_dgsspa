package com.dgsspa.comicverse.service;

import com.dgsspa.comicverse.config.ErrorMessagesProperties;
import com.dgsspa.comicverse.config.SearchMessagesProperties;
import com.dgsspa.comicverse.config.SuccessMessagesProperties;
import com.dgsspa.comicverse.dto.ApiResponseDTO;
import com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.dto.SearchResponseDTO;
import com.dgsspa.comicverse.exception.ResourceNotFoundException;
import com.dgsspa.comicverse.mapper.FumettoMapper;
import com.dgsspa.comicverse.model.Fumetto;
import com.dgsspa.comicverse.repository.FumettoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        log.debug("Avvio recupero di tutti i fumetti");
        List<FumettoDTO> risultati = fumettoRepository.findAll().stream()
                .map(fumettoMapper::toDTO)
                .collect(Collectors.toList());
        log.info("Recupero completato: {} fumetti trovati", risultati.size());
        return risultati;
    }

    public FumettoDTO recuperaFumettoPerId(Integer id) {
        log.debug("Recupero fumetto con id={}", id);
        return fumettoRepository.findById(id)
                .map(fumettoMapper::toDTO)
                .orElseThrow(() -> {
                    log.info("Fumetto non trovato durante recupero: id={}", id);
                    return new ResourceNotFoundException(
                            String.format(errorMessagesProperties.getNotFound(), "Fumetto", id));
                });
    }

    @Transactional
    public ApiResponseDTO<FumettoDTO> inserisciNuovoFumetto(FumettoDTO fumettoDTO) {
        log.debug("Inserimento nuovo fumetto: titolo={}", fumettoDTO.getTitolo());
        Fumetto fumetto = fumettoMapper.toEntity(fumettoDTO);
        Fumetto saved = fumettoRepository.save(fumetto);
        log.info("Nuovo fumetto inserito con id={} titolo={}", saved.getId(), saved.getTitolo());
        return new ApiResponseDTO<>(
                fumettoMapper.toDTO(saved),
                String.format(successMessagesProperties.getCreated(), "Fumetto")
        );
    }

    @Transactional
    public ApiResponseDTO<FumettoDTO> aggiornaFumetto(Integer id, FumettoDTO fumettoDTO) {
        log.debug("Aggiornamento fumetto con id={}", id);
        return fumettoRepository.findById(id)
                .map(existing -> {
                    fumettoMapper.updateEntityFromDTO(fumettoDTO, existing);
                    Fumetto updated = fumettoRepository.save(existing);
                    log.info("Fumetto aggiornato con id={} titolo={}", updated.getId(), updated.getTitolo());
                    return new ApiResponseDTO<>(
                            fumettoMapper.toDTO(updated),
                            String.format(successMessagesProperties.getUpdated(), "Fumetto")
                    );
                })
                .orElseThrow(() -> {
                    log.info("Fumetto non trovato durante aggiornamento: id={}", id);
                    return new ResourceNotFoundException(
                            String.format(errorMessagesProperties.getNotFound(), "Fumetto", id));
                });
    }

    @Transactional
    public String eliminaFumetto(Integer id) {
        log.debug("Eliminazione fumetto con id={}", id);
        if (!fumettoRepository.deleteById(id)) {
            log.info("Fumetto non trovato durante eliminazione: id={}", id);
            throw new ResourceNotFoundException(
                    String.format(errorMessagesProperties.getNotFound(), "Fumetto", id));
        }
        String messaggio = String.format(successMessagesProperties.getDeleted(), "Fumetto", id);
        log.info("Eliminazione completata: {}", messaggio);
        return messaggio;
    }

    public SearchResponseDTO<FumettoDTO> cercaPerFiltri(String titolo, LocalDateTime data) {
        log.debug("Ricerca fumetti per filtri: titolo={} data={}", titolo, data);
        List<FumettoDTO> risultati = fumettoRepository.searchByFiltri(titolo, data).stream()
                .map(fumettoMapper::toDTO)
                .collect(Collectors.toList());
        log.info("Ricerca completata: {} risultati per titolo={} data={}", risultati.size(), titolo, data);
        return buildSearchResponse(
                risultati,
                String.format(searchMessagesProperties.getNoResults(), "fumetto", titolo)
        );
    }

    public SearchResponseDTO<FumettoDTO> stampaTuttiFumettiResponse() {
        List<FumettoDTO> tutti = stampaTuttiFumetti();
        return buildSearchResponse(
                tutti,
                String.format(searchMessagesProperties.getNoResults(), "fumetto", "")
        );
    }

    private SearchResponseDTO<FumettoDTO> buildSearchResponse(List<FumettoDTO> risultati, String emptyMessage) {
        if (risultati.isEmpty()) {
            return new SearchResponseDTO<>(risultati, 0, emptyMessage);
        }
        return new SearchResponseDTO<>(risultati, risultati.size(), null);
    }
}