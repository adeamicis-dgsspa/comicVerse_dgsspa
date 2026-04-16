package com.dgsspa.comicverse.model;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "INVENTARIO")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INVENTARIO")
    @SequenceGenerator(name = "SEQ_INVENTARIO", sequenceName = "S_INVENTARIO", allocationSize = 1)
    @Column(name = "ID_ARTICOLO")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_FUMETTO", referencedColumnName = "ID_FUMETTO", unique = true, nullable = false)
    private Fumetto fumetto;

    @Column(name = "PREZZO_VENDITA")
    private Float prezzoVendita;

    @Column(name = "GIACENZA")
    private Integer giacenza;
    /*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "DETTAGLIO_VENDITA",
            joinColumns = @JoinColumn(name = "FK_ARTICOLO"),
            inverseJoinColumns = @JoinColumn(name = "FK_VENDITA")
    )
    /*private List<Vendita> vendite = new ArrayList<>();*/

    public Inventario() {}

    public Integer getId() { return id; }
    public Fumetto getFumetto() { return fumetto; }
    public Float getPrezzoVendita() { return prezzoVendita; }
    public Integer getGiacenza() { return giacenza; }
    /*public List<Vendita> getVendite() { return vendite; }*/

    public void setId(Integer id) { this.id = id; }
    public void setFumetto(Fumetto fumetto) { this.fumetto = fumetto; }
    public void setPrezzoVendita(Float prezzoVendita) { this.prezzoVendita = prezzoVendita; }
    public void setGiacenza(Integer giacenza) { this.giacenza = giacenza; }
    /*public void setVendite(List<Vendita> vendite) { this.vendite = vendite; }*/
}