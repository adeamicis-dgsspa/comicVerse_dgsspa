/*package com.dgsspa.comicverse.dto;

import jakarta.validation.constraints.*;

public class AutoreDTO {

    @NotBlank(message = "Campo Obbligatorio")
    @Pattern(regexp = "^[\\p{L}'\\s]+$", message = "Il nome dell'autore può contenere solo lettere, spazi e apostrofi")
    private String nome_cognome;

    @NotBlank(message = "Campo Obbligatorio")
    private String biografia;


    private boolean vivo;

    public AutoreDTO() {
    }

    public AutoreDTO(String nome_cognome, String biografia, boolean vivo) {
        this.nome_cognome=nome_cognome;
        this.biografia=biografia;
        this.vivo=vivo;
    }

    public String getNome_cognome() {
        return nome_cognome;
    }

    public void setNome_cognome(String nome_cognome) {
        this.nome_cognome = nome_cognome;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

}*/
