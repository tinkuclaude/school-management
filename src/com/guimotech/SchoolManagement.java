package com.guimotech;

import com.guimotech.config.DBConfig;
import com.guimotech.gui.swing.SglEleve;
import com.guimotech.gui.swing.SglTrimestre;

import java.sql.SQLException;

public class SchoolManagement {
    public static void main(String[] args) {
        System.out.println("Welcome To School Maganament System");

        if(DBConfig.getInstance().openConnection(
                "127.0.0.1", "5432", "db_exemple1",
                "postgres", "postgres") == null) {

            System.out.println("Echec de connexion a la base de données ...");
            System.out.println("Verifier vos parametres de connexion.");
        }

        boolean modele = true;
        SglTrimestre sglTerm = SglTrimestre.getInstance(null, modele);

        sglTerm.setVisible(true);

        boolean model = true;
         SglEleve sglEleve = SglEleve.getInstance(null, model);

        sglEleve.setVisible(true);
//        sglTerm.dispose();

/*
        TrimestreUX trimUX = new TrimestreUX();
        trimUX.afficherTout();
        trimUX.ajouter();
        trimUX.supprimer();
//*/
        if(model)
            try {
                DBConfig.getInstance().close();
            } catch (SQLException e) {
                System.out.println("La connexion à la BD a echouée");
            }
        System.out.println("Merci d'avoir utilisé ce Systeme");
    }
}
