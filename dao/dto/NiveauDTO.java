package com.guimotech.dao.dto;

import com.guimotech.dao.model.Niveau;

public class NiveauDTO {

//    public NiveauDTO(Long id, String code, String intitule, Integer frais_insciption) {
//
//    }
//
//    public NiveauDTO(Long id, String code, String intitule, Integer fraisInsciption) {
//    }

    public NiveauDTO(long id, String code, String intitule, Integer fraisInsciption) {
    }

    public void setCode(Object code) {
    }

       private long id;
        private String code;
        private String intitule;
        private Integer frais_insciption;

        public NiveauDTO(Long id,String code, String intitule, Integer frais_insciption ) {
            this.id=id;
            this.code = code;
            this.intitule = intitule;
            this.frais_insciption=frais_insciption;
        }

        public NiveauDTO() {
        }

    public long getId() {
        return id;
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

        @Override
        public String toString() {
            return "Id: "+id+ "Code: "+code+" Intitule: "+intitule+"frais_insciption:"+frais_insciption;
        }


}
