package com.dgsspa.comicverse.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name="FUMETTO")
public class Fumetto{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "S_VT_ANAG_RI", allocationSize = 1)
    @Column (name = "ID_FUMETTO") //HO DOVUTO METTERE QUESTA ANNOTAZIONE PERCHè HO CHIAMATO L'ID DIVERSAMENTE E NON LO TROVAVA
    private Integer id;

    private String titolo;
    private String editore;
    private Integer volume;
    @Column (name = "DATA_PUBBLICAZIONE") //IDEM DI PRIMA
    private LocalDateTime dataPubblicazione;
    private String descrizione;

    /*@OneToOne(mappedBy = "fumetto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventario inventario;*/

    /*@ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
            name = "fumetto_autore",
            joinColumns = @JoinColumn (name = "fumetto_id"),
            inverseJoinColumns = @JoinColumn(name = "autore_id")
    )
    private List<Autore> autori;

    @ManyToMany(mappedBy = "fumetti", fetch = FetchType.LAZY)
    private List<Genere> generi = new ArrayList<>();*/


    public Fumetto(){}

/*----------------------------------------------------------------------------------------*/

    public Integer getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getEditore() {
        return editore;
    }

    public Integer getVolume() {
        return volume;
    }

    public LocalDateTime getDataPubblicazione() {
        return dataPubblicazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    /*public List<Autore> getAutori() {
        return autori;
    }

    public List<Genere> getGeneri() {
        return generi;
    }*/

/*----------------------------------------------------------------------------------------*/

    public void setId(Integer id) {
        this.id = id;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public void setDataPubblicazione(LocalDateTime dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /*public void setAutori(List<Autore> autori) {
        this.autori = autori;
    }

    public void setGeneri(List<Genere> generi) {
        this.generi = generi;
    }*/

}