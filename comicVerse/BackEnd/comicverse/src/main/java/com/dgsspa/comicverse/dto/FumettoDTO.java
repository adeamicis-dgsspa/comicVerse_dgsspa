package com.dgsspa.comicverse.dto;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class FumettoDTO{
    @NotBlank (message="Campo Obbligatorio")
    private String titolo;

    @NotBlank (message="Campo Obbligatorio")
    @Pattern(regexp="^[\\p{L}']+$", message="Il nome dell'editore può contenere solo lettere, spazi e apostrofi")
    private String editore;
    private Integer volume;
    private LocalDateTime dataPubblicazione;
    private String descrizione;

    @OneToOne(mappedBy = "fumetto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventario inventario;

    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
            name = "fumetto_autore",
            joinColumns = @JoinColumn (name = "fumetto_id"),
            inverseJoinColumns = @JoinColumn(name = "autore_id")
    )
    private List<Autore> autori;

    @ManyToMany(mappedBy = "fumetti", fetch = FetchType.LAZY)
    private List<Genere> generi = new ArrayList<>();


    public Fumetto(){}
