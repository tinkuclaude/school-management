package com.guimotech;

import com.guimotech.config.DBConfig;

import com.guimotech.gui.swing.FrmAccueil;

public class SchoolManagement {
    public static void main(String[] args) {
        System.out.println("Welcome To School Maganament System");

        if(DBConfig.getInstance().openConnection(
                "127.0.0.1", "5432", "db_exemple1",
                "postgres", "postgres") == null) {

            System.out.println("Echec de connexion a la base de données ...");
            System.out.println("Verifier vos parametres de connexion.");

            return;
        }

        FrmAccueil.getInstance().setVisible(true);

        System.out.println("Merci d'avoir utilisé ce Systeme");
    }
}
