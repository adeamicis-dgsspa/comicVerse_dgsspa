package com.dgsspa.comicverse.controller;

import com.dgsspa.comicverse.dto.FumettoDTO;
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

    @GetMapping("/{id}")
    public FumettoDTO getFumetto(@PathVariable Integer id) {
        return fumettoService.recuperaFumettoPerId(id);
    }

    @GetMapping("/ricerca/titolo")
    public List<FumettoDTO> getFumettiPerTitoloIniziale(@RequestParam("iniziaCon") String iniziaCon) {
        return fumettoService.cercaPerTitoloCheIniziaCon(iniziaCon);
    }

    @GetMapping("/ricerca/data")
    public List<FumettoDTO> getFumettiPubblicatiDopo(
            @RequestParam("dopo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dopo) {
        return fumettoService.cercaPubblicatiDopo(dopo);
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaFumetto(@PathVariable Integer id) {
        fumettoService.eliminaFumetto(id);
    }
}
