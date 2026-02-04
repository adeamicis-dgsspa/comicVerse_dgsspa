/*package com.dgsspa.comicverse.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table (name="genere")
public class Genere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManytoMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "genere_fumetto",
            joinColumns = @JoinColumn(name = "genere_id"),
            inverseJoinColumns = @JoinColumn(name = "fumetto_id")
    )
    private List<Fumetto> fumetti;

    public Genere() {
    }
/*----------------------------------------------------------------------------------------*/
/*
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Fumetto> getFumetti() {
        return fumetti;
    }

/*----------------------------------------------------------------------------------------*/
/*
    public void setId(Integer id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFumetti(List<Fumetto> fumetti) {
        this.fumetti = fumetti;
    }
}*/