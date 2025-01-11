package com.guimotech.gui.cmd;

import com.guimotech.dao.dto.TrimestreDTO;
import com.guimotech.service.TrimestreService;

import java.util.Scanner;

public class TrimestreUX {

    private TrimestreService trimService = TrimestreService.getInstance();

    public TrimestreUX() {
    }

    public void afficherTout() {
        System.out.println("Affichage des trimestre");
        trimService.getAll().forEach(
                t -> System.out.println(t.toString())
        );
    }

    public void ajouter() {
        System.out.println("Ajout d'un trimestre");
        Scanner scan = new Scanner(System.in);

        TrimestreDTO dto = new TrimestreDTO();

        System.out.print("Entrer le numero: ");
        dto.setNumero(scan.nextInt());
        scan.nextLine();

        System.out.print("Entrer l'intitulé: ");
        dto.setIntitule(scan.nextLine());

        try {
            trimService.save(dto);
            System.out.println("Trimestre enregistré avec succèss.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void supprimer() {

        System.out.println("Suppression d'un trimestre");
        Scanner scan = new Scanner(System.in);

        System.out.print("Entrer le numero: ");
        Integer key = scan.nextInt();
        scan.nextLine();

        try {
            trimService.delete(key);
            System.out.println("Trimestre supprimé avec succèss.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
