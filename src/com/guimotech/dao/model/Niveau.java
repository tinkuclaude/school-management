package com.guimotech.dao.model;

public class Niveau {
    private Long id;
    private String code;
    private String intitule;
    private Integer frais_inscription;

    public Niveau(Long id, String code, String intitule, Integer frais_insciption) {
        this.id=id;
        this.code = code;
        this.intitule = intitule;
        this.frais_inscription = frais_insciption;
    }

    public Niveau() {
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

    public Integer getFrais_inscription() {
        return frais_inscription;
    }

    public void setFrais_inscription(Integer frais_inscription) {

        this.frais_inscription = frais_inscription;
    }


}



