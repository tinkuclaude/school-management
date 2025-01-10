package com.guimotech.service;

import com.guimotech.dao.dto.TrimestreDTO;
import com.guimotech.dao.model.Trimestre;
import com.guimotech.dao.repos.TrimestreRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class TrimestreService {

    private static TrimestreService instance = null;
    public static TrimestreService getInstance() {
        if(instance == null) {
            instance = new TrimestreService();
        }
        return instance;
    }

    private TrimestreService() {}

    private TrimestreRepo trimRepo = TrimestreRepo.getInstance();

    private Trimestre convert(TrimestreDTO dto) {
        return new Trimestre(dto.getNumero(), dto.getIntitule());
    }

    private TrimestreDTO convert(Trimestre model) {
        return new TrimestreDTO(model.getNumero(), model.getIntitule());
    }

    private void map(TrimestreDTO dto, Trimestre model) {
        model.setNumero(dto.getNumero());
        model.setIntitule(dto.getIntitule());
        // calculer d'autre attribut qui ne sont dans DTO
    }

    public TrimestreDTO save(TrimestreDTO trimestreDTO) throws Exception {
        // verifier que toutes les informations obligatoire sont definies

        if(trimestreDTO.getNumero() == null) {
            // throw an Exception
            throw new Exception("Entrer le numero du trimestre");
        }

        if(trimestreDTO.getIntitule() == null ||
            trimestreDTO.getIntitule().equals("")) {
            // throw an Exception
            throw new Exception("Entrer l'intitulé du trimestre");
        }

        Trimestre trimestre = trimRepo.findById(trimestreDTO.getNumero());
        if(trimestre.getNumero() == null) {
            // create
            trimestre = convert(trimestreDTO);
        } else {
            // update
            map(trimestreDTO, trimestre);
        }

        trimestre = trimRepo.save(trimestre);

        return convert(trimestre);
    }

    public TrimestreDTO getTrimestre(Integer numero) throws Exception {
       Trimestre trim = trimRepo.findById(numero);
       if (trim.getNumero() == null) {
           // le trimestre n'existe pas
           throw new Exception("Le trimestre n'existre pas.");
       }
       return convert(trim);
    }

    public List<TrimestreDTO> getAll() {
        try {
            return trimRepo.findAll().stream().map(this::convert).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(Integer key) throws Exception {
        Trimestre trim = trimRepo.findById(key);
        if(trim.getNumero() == null) {
            throw new Exception("Cet trimestre n'existe pas.");
        }
        return trimRepo.delete(key);
    }

}
