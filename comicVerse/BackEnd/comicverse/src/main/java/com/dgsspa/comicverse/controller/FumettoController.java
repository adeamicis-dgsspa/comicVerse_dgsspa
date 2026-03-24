package com.dgsspa.comicverse.controller;

import com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.dto.MessageResponseDTO;
import com.dgsspa.comicverse.dto.SearchResponseDTO;
import com.dgsspa.comicverse.service.FumettoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fumetti")
public class FumettoController {

    private final FumettoService fumettoService;

    public FumettoController(FumettoService fumettoService) {
        this.fumettoService = fumettoService;
    }

    @GetMapping
    public List<FumettoDTO> getAllFumetti() {
        return fumettoService.stampaTuttiFumetti();
    }

    @GetMapping("/ricerca/{id}")
    public FumettoDTO getFumetto(@PathVariable Integer id) {
        return fumettoService.recuperaFumettoPerId(id);
    }

    @GetMapping("/ricerca/filtri")
    public SearchResponseDTO<FumettoDTO> getFumettiPerFiltri(
            @RequestParam(required = false) String titolo,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data) {
        if (titolo == null && data==null) {
            return fumettoService.stampaTuttiFumettiResponse();
        }
        return fumettoService.cercaPerFiltri(titolo,data);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FumettoDTO creaNuovoFumetto(@Valid @RequestBody FumettoDTO fumettoDTO) {
        return fumettoService.inserisciNuovoFumetto(fumettoDTO);
    }

    @PutMapping("/aggiorna/{id}")
    public FumettoDTO aggiornaFumetto(
            @PathVariable Integer id,
            @Valid @RequestBody FumettoDTO fumettoDTO) {
        return fumettoService.aggiornaFumetto(id, fumettoDTO);
    }

    @DeleteMapping("/elimina/{id}")
    public MessageResponseDTO eliminaFumetto(@PathVariable Integer id) {
        return new MessageResponseDTO(fumettoService.eliminaFumetto(id));
    }
}