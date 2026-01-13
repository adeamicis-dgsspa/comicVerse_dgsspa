package com.dgsspa.comicverse.controller;

import com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.service.FumettoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<FumettoDTO>> getAllFumetti() {
        return ResponseEntity.ok(fumettoService.stampaTuttiFumetti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FumettoDTO> getFumetto(@PathVariable Integer id) {
        try {
            FumettoDTO fumetto = fumettoService.recuperaFumettoPerId(id);
            return ResponseEntity.ok(fumetto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<FumettoDTO> creaNuovoFumetto(@Valid @RequestBody FumettoDTO fumettoDTO) {
        FumettoDTO saved = fumettoService.inserisciNuovoFumetto(fumettoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FumettoDTO> aggiornaFumetto(
            @PathVariable Integer id,
            @Valid @RequestBody FumettoDTO fumettoDTO) {
        try {
            FumettoDTO updated = fumettoService.aggiornaFumetto(id, fumettoDTO);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaFumetto(@PathVariable Integer id) {
        try {
            fumettoService.eliminaFumetto(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}