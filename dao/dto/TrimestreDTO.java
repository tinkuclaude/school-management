package com.guimotech.dao.dto;

public class TrimestreDTO {
    private Integer numero;
    private String intitule;

    public TrimestreDTO(Integer numero, String intitule) {
        this.numero = numero;
        this.intitule = intitule;
    }

    public TrimestreDTO() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    @Override
    public String toString() {
        return "Numero: "+numero+" Intitule: "+intitule;
    }
}
