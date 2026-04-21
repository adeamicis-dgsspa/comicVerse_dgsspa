package com.dgsspa.comicverse.service;

import com.dgsspa.comicverse.config.ErrorMessagesProperties;
import com.dgsspa.comicverse.config.SuccessMessagesProperties;
import com.dgsspa.comicverse.dto.ApiResponseDTO;
import com.dgsspa.comicverse.dto.InventarioDTO;
import com.dgsspa.comicverse.exception.ResourceNotFoundException;
import com.dgsspa.comicverse.mapper.InventarioMapper;
import com.dgsspa.comicverse.model.Fumetto;
import com.dgsspa.comicverse.model.Inventario;
import com.dgsspa.comicverse.repository.FumettoRepository;
import com.dgsspa.comicverse.repository.InventarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class InventarioService {

    private static final Logger log = LoggerFactory.getLogger(InventarioService.class); // ✅ fisso

    private final InventarioRepository inventarioRepository;
    private final FumettoRepository fumettoRepository;
    private final InventarioMapper inventarioMapper;
    private final ErrorMessagesProperties errorMessagesProperties;
    private final SuccessMessagesProperties successMessagesProperties;

    public InventarioService(InventarioRepository inventarioRepository,
                             FumettoRepository fumettoRepository,
                             InventarioMapper inventarioMapper,
                             ErrorMessagesProperties errorMessagesProperties,
                             SuccessMessagesProperties successMessagesProperties) {
        this.inventarioRepository = inventarioRepository;
        this.fumettoRepository = fumettoRepository;
        this.inventarioMapper = inventarioMapper;
        this.errorMessagesProperties = errorMessagesProperties;
        this.successMessagesProperties = successMessagesProperties;
    }

    public List<InventarioDTO> stampaTuttiArticoli() {
        log.debug("Avvio recupero di tutti gli articoli");
        List<InventarioDTO> risultati = inventarioRepository.findAll().stream()
                .map(inventarioMapper::toDTO)
                .collect(Collectors.toList());
        log.info("Recupero completato: {} articoli trovati", risultati.size());
        return risultati;
    }

    @Transactional
    public ApiResponseDTO<InventarioDTO> inserisciNuovoArticolo(InventarioDTO inventarioDTO) {
        log.debug("Inserimento nuovo articolo: nome={}", inventarioDTO.getNome());
        Inventario inventario = inventarioMapper.toEntity(inventarioDTO);
        Fumetto fumetto = fumettoRepository.findById(inventarioDTO.getIdFumetto())
                .orElseThrow(() -> new ResourceNotFoundException("Fumetto non trovato"));
        inventario.setFumetto(fumetto);
        Inventario saved = inventarioRepository.save(inventario);
        log.info("Nuovo articolo inserito con id={} nome={}", saved.getId(), saved.getNome());
        return new ApiResponseDTO<>(
                inventarioMapper.toDTO(saved),
                String.format(successMessagesProperties.getCreated(), "Inventario")
        );
    }

    @Transactional
    public ApiResponseDTO<InventarioDTO> aggiornaArticolo(Integer id, InventarioDTO inventarioDTO) {
        log.debug("Aggiornamento articolo con id={}", id);
        return inventarioRepository.findById(id)
                .map(existing -> {
                    inventarioMapper.updateEntityFromDTO(inventarioDTO, existing);
                    Fumetto fumetto = fumettoRepository.findById(inventarioDTO.getIdFumetto())
                            .orElseThrow(() -> new ResourceNotFoundException("Fumetto non trovato"));
                    existing.setFumetto(fumetto); // ✅ setFumetto non setFumetti
                    Inventario updated = inventarioRepository.save(existing);
                    log.info("Articolo aggiornato con id={} nome={}", updated.getId(), updated.getNome());
                    return new ApiResponseDTO<>(
                            inventarioMapper.toDTO(updated),
                            String.format(successMessagesProperties.getUpdated(), "Inventario")
                    );
                })
                .orElseThrow(() -> {
                    log.info("Articolo non trovato durante aggiornamento: id={}", id);
                    return new ResourceNotFoundException(
                            String.format(errorMessagesProperties.getNotFound(), "Inventario", id));
                });
    }

    @Transactional
    public String eliminaArticolo(Integer id) {
        log.debug("Eliminazione articolo con id={}", id);
        if (!inventarioRepository.deleteById(id)) {
            log.info("Articolo non trovato durante eliminazione: id={}", id);
            throw new ResourceNotFoundException(
                    String.format(errorMessagesProperties.getNotFound(), "Inventario", id));
        }
        String messaggio = String.format(successMessagesProperties.getDeleted(), "Inventario", id);
        log.info("Eliminazione completata: {}", messaggio);
        return messaggio;
    }
}