/*package com.dgsspa.comicverse.model;

import javax.persistence.*;
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
    private MetodoPagamento metodo;

    @ManyToMany(mappedBy = "vendite", fetch = FetchType.LAZY)
    private List<Inventario> inventari = new ArrayList<>();

    public Vendita() {
    }

    /*----------------------------------------------------------------------------------------*/
/*
    public Integer getId() {
        return id;
    }

    public LocalDateTime getDataOrdine() {
        return dataOrdine;
    }

    public Long getTotaleOrdine() {
        return totaleOrdine;
    }

    public MetodoPagamento getMetodo() {
        return metodo;
    }

    public List<Inventario> getInventari() {
        return inventari;
    }
*/
    /*----------------------------------------------------------------------------------------*/
/*
    public void setId(Integer id) {
        this.id = id;
    }

    public void setDataOrdine(LocalDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public void setTotaleOrdine(Long totaleOrdine) {
        this.totaleOrdine = totaleOrdine;
    }

    public void setMetodo(MetodoPagamento metodo) {
        this.metodo = metodo;
    }

    public void setInventari(List<Inventario> inventari) {
        this.inventari = inventari;
    }
}*/