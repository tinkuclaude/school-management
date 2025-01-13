package com.guimotech.dao.dto;

public class EleveDTO {
        private String matricule;
        private String nom;
        private String prenom;
        private String sexe;
        private  String datenaiss;

        public EleveDTO(String matricule, String nom, String prenom, String sexe, String datenaiss) {
            this.matricule = matricule;
            this.nom = nom;
            this.prenom = prenom;
            this.sexe = sexe;
            this.datenaiss = datenaiss;
        }

        public EleveDTO() {
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

    @Override
        public String toString() {
            return "matricule: "+ matricule +" nom: "+ nom +" prenom: "+ prenom +" sexe: "+ sexe +" datenaiss: "+ datenaiss;
        }

}

