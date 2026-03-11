package com.dgsspa.comicverse.controller;

import com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.dto.MessageResponseDTO;
import com.dgsspa.comicverse.dto.SearchResponseDTO;
import com.dgsspa.comicverse.service.FumettoService;
import org.springframework.http.HttpStatus;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
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

    @GetMapping("/ricerca/titolo")
    public SearchResponseDTO<FumettoDTO> getFumettiPerTitolo(@RequestParam("titolo") String titolo) {
        return fumettoService.cercaPerTitoloConEsito(titolo);
    }

    @GetMapping("/ricerca/data")
    public SearchResponseDTO<FumettoDTO> getFumettiPubblicatiDopo(
            @RequestParam("dopo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dopo) {
        return fumettoService.cercaPubblicatiDopoConEsito(dopo);
    }

    @PostMapping("/crea")
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

    @DeleteMapping("/cancella/{id}")
    public MessageResponseDTO eliminaFumetto(@PathVariable Integer id) {
        return new MessageResponseDTO(fumettoService.eliminaFumetto(id));
    }
}
