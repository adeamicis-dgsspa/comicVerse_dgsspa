package com.dgsspa.comicverse.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table (name="autore")
public class Autore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome_cognome;
    private String biografia;
    private boolean vivo;

    @ManyToMany(mappedBy = "autori", fetch = FetchType.LAZY)
    @JsonBackReference("fumetto-autori")
    private List<Fumetto> fumetti = new ArrayList<>();


    public Autore() {}

/*----------------------------------------------------------------------------------------*/

    public Integer getId() {
        return id;
    }

    public String getNome_cognome() {
        return nome_cognome;
    }

    public String getBiografia() {
        return biografia;
    }

    public boolean isVivo() {
        return vivo;
    }

    public List<Fumetto> getFumetti() {
        return fumetti;
    }

/*----------------------------------------------------------------------------------------*/

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome_cognome(String nome_cognome) {
        this.nome_cognome = nome_cognome;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void setFumetti(List<Fumetto> fumetti) {
        this.fumetti = fumetti;
    }
}