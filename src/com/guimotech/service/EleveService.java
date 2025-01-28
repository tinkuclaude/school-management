package com.guimotech.service;

import com.guimotech.dao.dto.EleveDTO;
import com.guimotech.dao.model.Eleve;
import com.guimotech.dao.repos.EleveRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class EleveService {
    private static EleveService instance = null;
    public static EleveService getInstance() {
        if(instance == null) {
            instance = new EleveService();
        }
        return instance;
    }

    private EleveService() {}

    private EleveRepo eleveRepo = EleveRepo.getInstance();

    private Eleve convert(EleveDTO dto) {
        return new Eleve(dto.getId(), dto.getMatricule(), dto.getNom(), dto.getPrenom(), dto.getSexe(), dto.getDatenaiss(), dto.getTelephone());
    }

    private EleveDTO convert(Eleve model) {
        return new EleveDTO(model.getId(), model.getMatricule(), model.getNom(), model.getPrenom(), model.getSexe(), model.getDatenaiss(), model.getTelephone());
    }

    private void map(EleveDTO dto, Eleve model) {
        model.setId(dto.getId());
        model.setMatricule(dto.getMatricule());
        model.setNom(dto.getNom());
        model.setPrenom(dto.getPrenom());
        model.setSexe(dto.getSexe());
        model.setDatenaiss(dto.getDatenaiss());
        model.setTelephone(dto.getTelephone());
        // calculer d'autre attribut qui ne sont dans DTO
    }

    public EleveDTO save(EleveDTO eleveDTO) throws Exception {
        // verifier que toutes les informations obligatoire sont definies

        if(eleveDTO.getMatricule() == null ||
                eleveDTO.getMatricule().trim().equals("")) {
            // throw an Exception
            throw new Exception("Entrer le matricule de l'eleve");
        }

        if(eleveDTO.getNom() == null ||
                eleveDTO.getNom().trim().equals("")) {
            // throw an Exception
            throw new Exception("Entrer le nom de l'eleve");
        }

        if(eleveDTO.getSexe() == null ||
                eleveDTO.getSexe() == -1) {
            // throw an Exception
            throw new Exception("Entrer le sexe de l'eleve");
        }

//        if(eleveDTO.getDatenaiss() == null) {
//            // throw an Exception
//            throw new Exception("Entrer la date de naissance de l'eleve");
//        }

        Eleve eleve;
        if(eleveDTO.getId() == null) {
            // create
            eleve = new Eleve();
//            eleve = convert(eleveDTO);
        } else {
            // update
            eleve = eleveRepo.findById(eleveDTO.getMatricule());
        }

        map(eleveDTO, eleve);
        eleve  = eleveRepo.save(eleve);

        return convert(eleve);
    }

    public EleveDTO getEleve(String matricule) throws Exception {
        Eleve eleve = eleveRepo.findById(matricule);
        if (eleve.getMatricule() == null) {
            // l'eleve n'existe pas
            throw new Exception("L'eleve n'existre pas.");
        }
        return convert(eleve);
    }

    public EleveDTO getEleve(Long id) throws Exception {
        Eleve eleve = eleveRepo.findById(id);
        if (eleve.getId() == null) {
            // l'eleve n'existe pas
            throw new Exception("L'eleve n'existre pas.");
        }
        return convert(eleve);
    }

    public List<EleveDTO> getAll() {
        try {
            return eleveRepo.findAll().stream().map(this::convert).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String key) throws Exception {
        Eleve eleve = eleveRepo.findById(key);
        if(eleve.getMatricule() == null) {
            throw new Exception("Cet eleve n'existe pas.");
        }
        return eleveRepo.delete(key);
    }

    public boolean delete(Long key) throws Exception {
        Eleve eleve = eleveRepo.findById(key);
        if(eleve.getId() == null) {
            throw new Exception("Cet eleve n'existe pas.");
        }
        return eleveRepo.delete(key);
    }

}
