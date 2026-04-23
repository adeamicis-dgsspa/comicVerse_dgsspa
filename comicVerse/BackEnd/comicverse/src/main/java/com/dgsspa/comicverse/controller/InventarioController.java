package com.dgsspa.comicverse.controller;

import com.dgsspa.comicverse.dto.ApiResponseDTO;
import com.dgsspa.comicverse.dto.InventarioDTO;
import com.dgsspa.comicverse.dto.MessageResponseDTO;
import com.dgsspa.comicverse.service.InventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    private final InventarioService inventarioService;

        public InventarioController(InventarioService inventarioService) {
            this.inventarioService = inventarioService;
        }

        @GetMapping
        public List<InventarioDTO> getAllArticolo() {
            return inventarioService.stampaTuttiArticoli();
        }

        @GetMapping("/ricerca/{id}")
            public InventarioDTO getArticoloPerId(@PathVariable Integer id) {
            return inventarioService.recuperaArticoloPerId(id);
        }

        @PostMapping("/crea")
        @ResponseStatus(HttpStatus.CREATED)
        public ApiResponseDTO<InventarioDTO> creaNuovoArticolo(@Valid @RequestBody InventarioDTO inventarioDTO) {
            return inventarioService.inserisciNuovoArticolo(inventarioDTO);
        }

        @PutMapping("/aggiorna/{id}")
        public ApiResponseDTO<InventarioDTO> aggiornaArticolo(
                @PathVariable Integer id,
                @Valid @RequestBody InventarioDTO inventarioDTO) {
            return inventarioService.aggiornaArticolo(id, inventarioDTO);
        }

        @DeleteMapping("/elimina/{id}")
        public MessageResponseDTO eliminaArticolo(@PathVariable Integer id) {
            return new MessageResponseDTO(inventarioService.eliminaArticolo(id));
        }

        @GetMapping("/totale/articoli")
        public Integer getTotaleArticoli() {
            return inventarioService.recuperaTotaleArticoli();
        }
        @GetMapping("/mediaPV")
        public BigDecimal getMediaPrezzoArticoli() {
            return inventarioService.mediaPrezzoArticoli();
        }
}
