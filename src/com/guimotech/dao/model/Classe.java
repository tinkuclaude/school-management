package com.guimotech.dao.model;

public class Classe {
    private Long id;
    private String code;
    private String intitule;
    private String niveau;

    public Classe(Long id, String code, String intitule, String niveau) {
        this.id=id;
        this.code = code;
        this.intitule = intitule;
        this.niveau = niveau;
    }

    public Classe() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}





