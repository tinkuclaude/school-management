package com.guimotech.dao.model;

import java.sql.Date;

public class Eleve {

    private Long id;
    private String matricule;
    private String nom;
    private String prenom;
    private Integer sexe;
    private Date datenaiss;
    private String telephone;

    public Eleve(Long id, String matricule, String nom, String prenom, Integer sexe, Date datenaiss, String telephone) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.datenaiss = datenaiss;
        this.telephone = telephone;
    }

    public Eleve() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {

        this.matricule = matricule;
    }

    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {

        this.prenom = prenom;
    }

    public Integer getSexe() {

        return sexe;
    }

    public void setSexe(Integer sexe) {

        this.sexe = sexe;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
