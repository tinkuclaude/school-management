package com.guimotech.dao.dto;

public class NiveauDTO {

        private Long id;
        private String code;
        private String intitule;
        private Integer frais_inscription;

        public NiveauDTO(Long id,String code, String intitule, Integer frais_insciption ) {
            this.id=id;
            this.code = code;
            this.intitule = intitule;
            this.frais_inscription =frais_insciption;
        }

        public NiveauDTO() {
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

        @Override
        public String toString() {
            return "Id: "+id+ "Code: "+code+" Intitule: "+intitule+"frais_insciption:"+ frais_inscription;
        }


    }


