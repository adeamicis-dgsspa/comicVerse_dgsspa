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

    private List<FumettoDTO> fumetti = new ArrayList<>();

    public GenereDTO() {}

    public GenereDTO(Integer id, String nome, List<FumettoDTO> fumetti) {
        this.id = id;
        this.nome = nome;
        this.fumetti = fumetti;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<FumettoDTO> getFumetti() {
        return fumetti;
    }

    public void setFumetti(List<FumettoDTO> fumetti) {
        this.fumetti = fumetti;
    }
}