package com.guimotech.service;

import com.guimotech.dao.dto.EnseignantDTO;
import com.guimotech.dao.model.Enseignant;
import com.guimotech.dao.repos.EnseignantRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class EnseignantService {
    private static EnseignantService instance = null;
    public static EnseignantService getInstance() {
        if(instance == null) {
            instance = new EnseignantService();
        }
        return instance;
    }

    private EnseignantService() {}

    private EnseignantRepo enseignantRepo = EnseignantRepo.getInstance();

    private Enseignant convert(EnseignantDTO dto) {
        return new Enseignant(dto.getId(), dto.getMatricule(), dto.getNom(), dto.getPrenom(), dto.getCivilite(), dto.getDatenaiss(), dto.getTelephone());
    }

    private EnseignantDTO convert(Enseignant model) {
        return new EnseignantDTO(model.getId(), model.getMatricule(), model.getNom(), model.getPrenom(), model.getCivilite(), model.getDatenaiss(), model.getTelephone());
    }

    private void map(EnseignantDTO dto, Enseignant model) {
        model.setId(dto.getId());
        model.setMatricule(dto.getMatricule());
        model.setNom(dto.getNom());
        model.setPrenom(dto.getPrenom());
        model.setCivilite(dto.getCivilite());
        model.setDatenaiss(dto.getDatenaiss());
        model.setTelephone(dto.getTelephone());
        // calculer d'autre attribut qui ne sont dans DTO
    }

    public EnseignantDTO save(EnseignantDTO enseignantDTO) throws Exception {
        // verifier que toutes les informations obligatoire sont definies

        if(enseignantDTO.getMatricule() == null ||
                enseignantDTO.getMatricule().trim().equals("")) {
            // throw an Exception
            throw new Exception("Entrer le matricule de l'enseignant");
        }

        if(enseignantDTO.getNom() == null ||
                enseignantDTO.getNom().trim().equals("")) {
            // throw an Exception
            throw new Exception("Entrer le nom de l'enseignant");
        }

        if(enseignantDTO.getCivilite() == null ||
                enseignantDTO.getCivilite() == -1) {
            // throw an Exception
            throw new Exception("Entrer la civilite de l'enseignant");
        }

//        if(eleveDTO.getDatenaiss() == null) {
//            // throw an Exception
//            throw new Exception("Entrer la date de naissance de l'eleve");
//        }

        Enseignant enseignant;
        if(enseignantDTO.getId() == null) {
            // create
            enseignant = new Enseignant();
//            enseignant = convert(enseignantDTO);
        } else {
            // update
            enseignant = enseignantRepo.findById(enseignantDTO.getMatricule());
        }

        map(enseignantDTO, enseignant);
        enseignant  = enseignantRepo.save(enseignant);

        return convert(enseignant);
    }

    public EnseignantDTO getEnseignant(String matricule) throws Exception {
        Enseignant enseignant = enseignantRepo.findById(matricule);
        if (enseignant.getMatricule() == null) {
            // l'enseignant n'existe pas
            throw new Exception("L'enseignant n'existre pas.");
        }
        return convert(enseignant);
    }

    public EnseignantDTO getEnseignant(Long id) throws Exception {
        Enseignant enseignant = enseignantRepo.findById(id);
        if (enseignant.getId() == null) {
            // l'enseignant n'existe pas
            throw new Exception("L'enseignant n'existre pas.");
        }
        return convert(enseignant);
    }

    public List<EnseignantDTO> getAll() {
        try {
            return enseignantRepo.findAll().stream().map(this::convert).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String key) throws Exception {
        Enseignant enseignant = enseignantRepo.findById(key);
        if(enseignant.getMatricule() == null) {
            throw new Exception("Cet enseignant n'existe pas.");
        }
        return enseignantRepo.delete(key);
    }

    public boolean delete(Long key) throws Exception {
        Enseignant enseignant = enseignantRepo.findById(key);
        if(enseignant.getId() == null) {
            throw new Exception("Cet enseignant n'existe pas.");
        }
        return enseignantRepo.delete(key);
    }

}
