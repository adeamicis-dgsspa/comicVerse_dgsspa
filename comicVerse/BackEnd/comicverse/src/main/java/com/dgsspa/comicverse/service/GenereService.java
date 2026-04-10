package com.dgsspa.comicverse.service;

import com.dgsspa.comicverse.config.ErrorMessagesProperties;
import com.dgsspa.comicverse.config.SuccessMessagesProperties;
import com.dgsspa.comicverse.dto.ApiResponseDTO;
import com.dgsspa.comicverse.dto.GenereDTO;
import com.dgsspa.comicverse.exception.ResourceNotFoundException;
import com.dgsspa.comicverse.mapper.GenereMapper;
import com.dgsspa.comicverse.model.Fumetto;
import com.dgsspa.comicverse.model.Genere;
import com.dgsspa.comicverse.repository.FumettoRepository;
import com.dgsspa.comicverse.repository.GenereRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class GenereService {

    private static final Logger log = LoggerFactory.getLogger(GenereService.class);

    private final GenereRepository genereRepository;
    private final FumettoRepository fumettoRepository;
    private final GenereMapper genereMapper;
    private final ErrorMessagesProperties errorMessagesProperties;
    private final SuccessMessagesProperties successMessagesProperties;

    public GenereService(GenereRepository genereRepository,
                         FumettoRepository fumettoRepository,
                         GenereMapper genereMapper,
                         ErrorMessagesProperties errorMessagesProperties,
                         SuccessMessagesProperties successMessagesProperties) {
        this.genereRepository = genereRepository;
        this.fumettoRepository = fumettoRepository;
        this.genereMapper = genereMapper;
        this.errorMessagesProperties = errorMessagesProperties;
        this.successMessagesProperties = successMessagesProperties;
    }

    public List<GenereDTO> stampaTuttiGeneri() {
        log.debug("Avvio recupero di tutti i generi");
        List<GenereDTO> risultati = genereRepository.findAll().stream()
                .map(genereMapper::toDTO)
                .collect(Collectors.toList());
        log.info("Recupero completato: {} generi trovati", risultati.size());
        return risultati;
    }

    @Transactional
    public ApiResponseDTO<GenereDTO> inserisciNuovoGenere(GenereDTO genereDTO) {
        log.debug("Inserimento nuovo genere: nome={}", genereDTO.getNome());
        Genere genere = genereMapper.toEntity(genereDTO);
        List<Fumetto> fumetti = recuperaFumetti(genereDTO.getIdFumetti());
        genere.setFumetti(fumetti);
        Genere saved = genereRepository.save(genere);
        log.info("Nuovo genere inserito con id={} nome={}", saved.getId(), saved.getNome());
        return new ApiResponseDTO<>(
                genereMapper.toDTO(saved),
                String.format(successMessagesProperties.getCreated(), "Genere")
        );
    }

    @Transactional
    public ApiResponseDTO<GenereDTO> aggiornaGenere(Integer id, GenereDTO genereDTO) {
        log.debug("Aggiornamento genere con id={}", id);
        return genereRepository.findById(id)
                .map(existing -> {
                    genereMapper.updateEntityFromDTO(genereDTO, existing);
                    List<Fumetto> fumetti = recuperaFumetti(genereDTO.getIdFumetti());
                    existing.setFumetti(fumetti);
                    Genere updated = genereRepository.save(existing);
                    log.info("Genere aggiornato con id={} nome={}", updated.getId(), updated.getNome());
                    return new ApiResponseDTO<>(
                            genereMapper.toDTO(updated),
                            String.format(successMessagesProperties.getUpdated(), "Genere")
                    );
                })
                .orElseThrow(() -> {
                    log.info("Genere non trovato durante aggiornamento: id={}", id);
                    return new ResourceNotFoundException(
                            String.format(errorMessagesProperties.getNotFound(), "Genere", id));
                });
    }

    @Transactional
    public String eliminaGenere(Integer id) {
        log.debug("Eliminazione genere con id={}", id);
        if (!genereRepository.deleteById(id)) {
            log.info("Genere non trovato durante eliminazione: id={}", id);
            throw new ResourceNotFoundException(
                    String.format(errorMessagesProperties.getNotFound(), "Genere", id));
        }
        String messaggio = String.format(successMessagesProperties.getDeleted(), "Genere", id);
        log.info("Eliminazione completata: {}", messaggio);
        return messaggio;
    }

    private List<Fumetto> recuperaFumetti(List<Integer> idFumetti) {
        return idFumetti.stream()
                .map(idFumetto -> fumettoRepository.findById(idFumetto)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                String.format(errorMessagesProperties.getNotFound(), "Fumetto", idFumetto))))
                .collect(Collectors.toList());
    }
}