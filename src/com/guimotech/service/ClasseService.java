package com.guimotech.service;

import com.guimotech.dao.dto.ClasseDTO;
import com.guimotech.dao.dto.NiveauDTO;
import com.guimotech.dao.model.Classe;
import com.guimotech.dao.model.Niveau;
import com.guimotech.dao.repos.ClasseRepo;
import com.guimotech.dao.repos.NiveauRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ClasseService {
    private static ClasseService instance = null;
    public static ClasseService getInstance() {
        if(instance == null) {
            instance = new ClasseService();
        }
        return instance;
    }

    private ClasseService() {}

    private static ClasseRepo classeRepo = ClasseRepo.getInstance();

    private Classe convert(ClasseDTO dto) {

        return new Classe(dto.getId(), dto.getCode(), dto.getIntitule(), dto.getNiveau());
    }

    private ClasseDTO convert(Classe model) {

        return new ClasseDTO(model.getId(),model.getCode(), model.getIntitule(),model.getNiveau());
    }

    private void map(ClasseDTO dto, Classe model) {
        model.setId(dto.getId());
        model.setCode(dto.getCode());
        model.setIntitule(dto.getIntitule());
        model.setNiveau(dto.getNiveau());
        // calculer d'autre attribut qui ne sont dans DTO
    }

    public ClasseDTO save(ClasseDTO classeDTO) throws Exception {
        // verifier que toutes les informations obligatoire sont definies

        if(classeDTO.getCode() == null || classeDTO.getCode().isBlank())  {
            // throw an Exception
            throw new Exception("Entrer le code d'une classe");
        }

        if(classeDTO.getNiveau() == null || classeDTO.getNiveau().isBlank())  {
            // throw an Exception
            throw new Exception("Selectionner le niveau de la classe");
        }

        // throw an Exception
        if(classeDTO.getIntitule() == null ||
                classeDTO.getIntitule().isBlank()) throw new Exception("Entrer l'intitulé d'une classe");

        Classe classe;
        if(classeDTO.getId() == null) {
            // create
            classe = new Classe();
        } else {
            classe = classeRepo.findById(classeDTO.getId());
            // update
        }
        map(classeDTO, classe);
        classe = classeRepo.save(classe);

        return convert(classe);
    }

//    public static ClasseDTO getClasse(String code) throws Exception {
//        Classe classe = classeRepo.findById(code);
//        if (classe.getCode() == null || classe==null) {
//            // le niveau n'existe pas
//            throw new Exception("Le niveau n'existre pas.");
//        }
//        return convert(classe);
//    }

    public ClasseDTO getClasse(Long id) throws Exception {
        Classe classe = classeRepo.findById(id);
        if (classe.getId() == null) {
            // le niveau n'existe pas
            throw new Exception("La classe n'existre pas.");
        }
        return convert(classe);
    }


    public List<ClasseDTO> getAll() {
        try {
            return classeRepo.findAll().stream().map(this::convert).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(String key) throws Exception {
        Classe classe = classeRepo.findById(key);
        if(classe.getCode() == null) {
            throw new Exception("Cette classe n'existe pas.");
        }
        return classeRepo.delete(key);
    }

    public boolean delete(Long key) throws Exception {
        Classe classe = classeRepo.findById(key);
        if(classe.getId() == null) {
            throw new Exception("Cette classe n'existe pas.");
        }
        return classeRepo.delete(key);
    }

}





