package com.dgsspa.comicverse.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;


public class FumettoDTO {
    @NotBlank(message = "Campo Obbligatorio")
    private String titolo;

    @NotBlank(message = "Campo Obbligatorio")
    @Pattern(regexp = "^[\\p{L}'\\s]+$", message = "Il nome dell'editore può contenere solo lettere, spazi e apostrofi")
    private String editore;


    @NotNull(message = "Campo volume Obbligatorio")
    @Min(value = 1, message = "Devi inserire il numero del volume")
    private Integer volume;


    @PastOrPresent(message = "La data di pubblicazione non può superare la data di oggi")
    private LocalDateTime dataPubblicazione;


    private String descrizione;

    public FumettoDTO() {
    }

    public FumettoDTO(String titolo, String editore, LocalDateTime dataPubblicazione, String descrizione) {
        this.titolo=titolo;
        this.editore=editore;
        this.volume=volume;
        this.dataPubblicazione=dataPubblicazione;
        this.descrizione=descrizione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getEditore() {
        return editore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public LocalDateTime getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(LocalDateTime dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}