package com.guimotech.dao.model;

public class Trimestre {
    private Integer numero;
    private String intitule;

    public Trimestre(Integer numero, String intitule) {
        this.numero = numero;
        this.intitule = intitule;
    }

    public Trimestre() {
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

    public String getCode() {

        return null;
    }

    public int getFrais_inscription() {
 return 0;
}

}
