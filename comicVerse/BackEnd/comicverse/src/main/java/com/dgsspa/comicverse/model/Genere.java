package com.dgsspa.comicverse.model;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
@Entity
@Table(name = "GENERE")
public class Genere {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENERE")
    @SequenceGenerator(name = "SEQ_GENERE", sequenceName = "S_GENERE", allocationSize = 1)
    @Column(name = "ID_GENERE")
    private Integer id;
    private String nome;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "GENERE_FUMETTO",
            joinColumns = @JoinColumn(name = "FK_GENERE"),
            inverseJoinColumns = @JoinColumn(name = "FK_FUMETTO")
    )
    private List<Fumetto> fumetti = new ArrayList<>();
    public Genere() {}
    /*----------------------------------------------------------------------------------------*/
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
    public void setId(Integer id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setFumetti(List<Fumetto> fumetti) {
        this.fumetti = fumetti;
    }
}