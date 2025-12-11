package com.dgsspa.comicverse.controller;

import com.dgsspa.comicverse.dto.FumettoDTO;
import com.dgsspa.comicverse.service.FumettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fumetti")
public class FumettoController {
    @Autowired
    private FumettoService fumettoService;

    @GetMapping()
    public List<FumettoDTO> getFumetti() {
    }
        return FumettoService.stampaTuttiFumetti();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFumetto(@PathVariable Integer id) {
        FumettoDTO fumetto = fumettoService.recuperaFumettoPerId(id);
        if (fumetto != null) {
            return  ?ResponseEntity.ok(fumetto)
        }//mettere tutto nel try catch
        else {
            String messaggioErrore = "Fumetto con ID " + id + " non trovato nel database ComicVerse.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messaggioErrore);
        }
    }
    @PostMapping()
    public ResponseEntity<FumettoDTO> creaNuovoFumetto(@RequestBody FumettoDTO fumettoDTO) {
        FumettoDTO saved = fumettoService.inserisciNuovoFumetto(fumettoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @PutMapping("{/id}")
    public ResponseEntity<FumettoDTO> aggiornaFumetto(@PathVariable Integer id, @RequestBody FumettoDTO fumettoDTO){
        fumettoDTO.setIdFumetto(id);
        FumettoDTO updated = fumettoService.aggiornaFumetto(fumettoDTO);

        if (fumetto != null) {
            return  ?ResponseEntity.ok(updated)
        }//mettere tutto nel try catch
        else {
            String messaggioErrore = "Fumetto con ID " + id + " non trovato nel database ComicVerse.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messaggioErrore);
        }
    }
}