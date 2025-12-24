package com.dgsspa.comicverse.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class GenereDTO {
    @NotBlank(message = "Campo Obbligatorio")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Il nome dell'editore può contenere solo lettere")

    private String nome;

    public GenereDTO() {
    }

    public GenereDTO(String nome) {
        this.titolo = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}