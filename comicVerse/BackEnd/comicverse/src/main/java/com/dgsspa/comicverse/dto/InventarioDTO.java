package com.dgsspa.comicverse.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Min;
import java.math.BigDecimal;


public class InventarioDTO {
    private Integer id;
    @NotNull(message = "Il prezzo di vendita è obbligatorio")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di zero")
    @Digits(integer = 3, fraction = 2, message = "Formato prezzo non valido (massimo 3 cifre intere e 2 decimali)")
    private BigDecimal prezzoVendita;

    @NotNull(message = "Campo giacenza Obbligatorio")
    @Min(value = 1, message = "Devi inserire il numero del volume")
    private Integer giacenza;
    @NotNull(message = "Obbligatorio inserire l'id fumetto")
    private Integer idFumetto;

    public InventarioDTO(Integer idFumetto, Integer id, BigDecimal prezzoVendita, Integer giacenza) {
        this.idFumetto = idFumetto;
        this.id = id;
        this.prezzoVendita = prezzoVendita;
        this.giacenza = giacenza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrezzoVendita() {
        return prezzoVendita;
    }

    public void setPrezzoVendita(BigDecimal prezzoVendita) {
        this.prezzoVendita = prezzoVendita;
    }

    public Integer getGiacenza() {
        return giacenza;
    }

    public void setGiacenza(Integer giacenza) {
        this.giacenza = giacenza;
    }

    public Integer getIdFumetto() {
        return idFumetto;
    }

    public void setIdFumetto(Integer idFumetto) {
        this.idFumetto = idFumetto;
    }
}


