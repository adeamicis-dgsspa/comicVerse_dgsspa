package com.dgsspa.comicverse.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Entity
@Table (name="vendita")
public class Vendita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dataOrdine;
    private Long totaleOrdine;

    @Enumerated(EnumType.STRING)
    private MetodoOrdine metodo;

    @ManyToMany(mappedBy = "vendite", fetch = FetchType.LAZY)
    private List<Dettaglio> dettagli = new ArrayList<>();

    public Vendita() {
    }

/*----------------------------------------------------------------------------------------*/



    public Integer getId() {

        return id;
        }

    public LocalDateTime getDataOrdine() {

        return dataOrdine;
        }

    public Long getTotaleOrdine() {

        return totaleOrdine;
    }

    public MetodoOrdine getMetodo() {
        return metodo;
    }

    public List<Dettaglio> getDettagli() {
        return dettagli;
    }

/*----------------------------------------------------------------------------------------*/

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDataOrdine(LocalDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public void setTotaleOrdine(Long totaleOrdine) {
        this.totaleOrdine = totaleOrdine;
    }

    public void setMetodo(MetodoOrdine metodo) {
        this.metodo = metodo;
    }

    public void setDettagli(List<Dettaglio> dettagli) {
        this.dettagli = dettagli;
    }