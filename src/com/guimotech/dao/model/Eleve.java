package com.guimotech.dao.model;

public class Eleve {
//    private Long id;
    private String matricule;
    private String nom;
    private String prenom;
    private String sexe;
    private String datenaiss;

    public Eleve(String matricule, String nom, String prenom, String sexe, String datenaiss) {
//        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.datenaiss = datenaiss;
    }

    public Eleve() {
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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

    public String getSexe() {

        return sexe;
    }

    public void setSexe(String sexe) {

        this.sexe = sexe;
    }

    public String getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(String datenaiss) {
        this.datenaiss = datenaiss;
    }
}
