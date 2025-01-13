package com.guimotech.dao.repos.inter;

import com.guimotech.dao.model.Eleve;


import java.sql.SQLException;
import java.util.List;

public abstract class RepoAbsEleve <ModelType, PKType>{
    public RepoAbsEleve() {}

    boolean create(ModelType obj) throws SQLException {
        return true;
    }

    public abstract boolean create(Eleve obj) throws SQLException;

    public abstract boolean update(ModelType obj) throws SQLException;

    public abstract  ModelType save(ModelType obj) throws SQLException;

    public abstract ModelType findById(PKType key) throws SQLException;
    public abstract List<ModelType> findAll() throws SQLException;

    public abstract boolean delete(PKType key) throws SQLException;
    public abstract boolean deleteOne(ModelType obj) throws SQLException;
    public abstract  boolean deleteAll() throws SQLException;
}
