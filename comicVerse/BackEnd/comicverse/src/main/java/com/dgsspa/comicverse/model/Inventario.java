/*package com.dgsspa.comicverse.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table (name="inventario")
public class Inventario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_FUMETTO", referencedColumnName = "ID_FUMETTO", unique = true, nullable = false)
    private Fumetto fumetto;

    private Float prezzoVendita;
    private Integer giacenza;

    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
            name = "dettaglio_vendita",
            joinColumns = @JoinColumn (name = "articolo_id"),
            inverseJoinColumns = @JoinColumn(name = "vendita_id")
    )
    private List<Vendita> vendite;


    public Inventario(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fumetto getFumetto() {
        return fumetto;
    }

    public void setFumetto(Fumetto fumetto) {
        this.fumetto = fumetto;
    }

    public Float getPrezzoVendita() {
        return prezzoVendita;
    }

    public void setPrezzoVendita(Float prezzoVendita) {
        this.prezzoVendita = prezzoVendita;
    }

    public Integer getGiacenza() {
        return giacenza;
    }

    public void setGiacenza(Integer giacenza) {
        this.giacenza = giacenza;
    }

    public List<Vendita> getVendite() {
        return vendite;
    }

    public void setVendite(List<Vendita> vendite) {
        this.vendite = vendite;
    }
}    */