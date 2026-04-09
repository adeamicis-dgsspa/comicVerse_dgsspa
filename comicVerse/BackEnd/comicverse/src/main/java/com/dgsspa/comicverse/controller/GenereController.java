package com.dgsspa.comicverse.controller;

import com.dgsspa.comicverse.dto.ApiResponseDTO;
import com.dgsspa.comicverse.dto.GenereDTO;
import com.dgsspa.comicverse.dto.MessageResponseDTO;
import com.dgsspa.comicverse.service.GenereService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/genere")
public class GenereController {

    private final GenereService genereService;

    public GenereController(GenereService genereService) {
        this.genereService = genereService;
    }

    @GetMapping
    public List<GenereDTO> getAllGenere() {
        return genereService.stampaTuttiGeneri();
    }

    @PostMapping("/crea")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDTO<GenereDTO> creaNuovoGenere(@Valid @RequestBody GenereDTO genereDTO) {
        return genereService.inserisciNuovoGenere(genereDTO);
    }

    @PutMapping("/aggiorna/{id}")
    public ApiResponseDTO<GenereDTO> aggiornaGenere(
            @PathVariable Integer id,
            @Valid @RequestBody GenereDTO genereDTO) {
        return genereService.aggiornaGenere(id, genereDTO);
    }

    @DeleteMapping("/elimina/{id}")
    public MessageResponseDTO eliminaGenere(@PathVariable Integer id) {
        return new MessageResponseDTO(genereService.eliminaGenere(id));
    }
}