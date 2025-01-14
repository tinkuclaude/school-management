package com.guimotech.service;

import com.guimotech.dao.dto.NiveauDTO;
import com.guimotech.dao.model.Niveau;
import com.guimotech.dao.repos.NiveauRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class NiveauService  {

    private static NiveauService instance = null;
    public static NiveauService getInstance() {
        if(instance == null) {
            instance = new NiveauService();
        }
        return instance;
    }

    private NiveauService() {}

    private NiveauRepo niveauRepo = NiveauRepo.getInstance();

    private Niveau convert(NiveauDTO dto) {

        return new Niveau(dto.getCode(), dto.getIntitule());
    }

    private NiveauDTO convert(Niveau model) {

        return new NiveauDTO(model.getId(),model.getCode(), model.getIntitule(),model.getFrais_insciption());
    }

    private void map(NiveauDTO dto, Niveau model) {
        model.setId(dto.getId());
        model.setCode(dto.getCode());
        model.setIntitule(dto.getIntitule());
        model.setFrais_insciption(dto.getFrais_insciption());
        // calculer d'autre attribut qui ne sont dans DTO
    }

    public NiveauDTO save(NiveauDTO niveauDTO) throws Exception {
        // verifier que toutes les informations obligatoire sont definies

        if(niveauDTO.getCode() == null) {
            // throw an Exception
            throw new Exception("Entrer le code du niveau");
        }

        // throw an Exception
        if(niveauDTO.getIntitule() == null ||
                niveauDTO.getIntitule().equals("")) throw new Exception("Entrer l'intitulé du niveau");

        Niveau niveau = niveauRepo.findById(niveauDTO.getCode());
        if(niveau.getCode() == null) {
            // create
            niveau = convert(niveauDTO);
        } else {
            // update
            map(niveauDTO, niveau);
        }

        niveau = niveauRepo.save(niveau);

        return convert(niveau);
    }

    public NiveauDTO getNiveau(String code) throws Exception {
        Niveau niveau = niveauRepo.findById(code);
        if (niveau.getCode() == null) {
            // le niveau n'existe pas
            throw new Exception("Le niveau n'existre pas.");
        }
        return convert(niveau);
    }

    public List<NiveauDTO> getAll() {
        try {
            return niveauRepo.findAll().stream().map(this::convert).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String key) throws Exception {
        Niveau niveau = niveauRepo.findById(key);
        if(niveau.getCode() == null) {
            throw new Exception("Ce niveau n'existe pas.");
        }
        return niveauRepo.delete(key);
    }

}

