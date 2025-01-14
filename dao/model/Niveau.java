package com.guimotech.dao.model;

public class Niveau {
    private long id;
    private String code;
    private String intitule;
    private Integer frais_insciption;

    public Niveau(Long id, String code, String intitule, Integer frais_insciption) {
        this.id=id;
        this.code = code;
        this.intitule = intitule;
        this.frais_insciption=frais_insciption;
    }

    public Niveau() {
    }

    public Niveau(String code, String intitule) {

    }

    public void setId(long id) {
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

    public Integer getFrais_insciption() {
        return frais_insciption;
    }

    public void setFrais_insciption(Integer frais_insciption) {

        this.frais_insciption = frais_insciption;
    }

    public int getFrais_inscription() {
        return 0;
    }

    public long getId() {
        return 0;
    }
}

