package com.dgsspa.comicverse.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.ArrayList;

public class GenereDTO {

    private Integer id;

    @NotBlank(message = "Campo Obbligatorio")
    @Pattern(regexp = "^[\\p{L}'\\s]+$", message = "Il nome del genere può contenere solo lettere, spazi e apostrofi")
    private String nome;

    private List<Integer> idFumetti = new ArrayList<>();

    public GenereDTO() {}

    public GenereDTO(Integer id, String nome, List<Integer> idFumetti) {
        this.id = id;
        this.nome = nome;
        this.idFumetti = idFumetti;
    }

    public Integer getId() { return id; }
    public String getNome() { return nome; }
    public List<Integer> getIdFumetti() { return idFumetti; }

    public void setId(Integer id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIdFumetti(List<Integer> idFumetti) { this.idFumetti = idFumetti; }
}