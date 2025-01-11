package com.guimotech.dao.repos.inter;

import java.sql.SQLException;
import java.util.List;

public interface RepoInter<ModelType, PKType> {

    // CRUD operation
    // C: Create, R: Read, U: Update, D: Delete

    boolean create(ModelType obj) throws SQLException;
    boolean update(ModelType obj) throws SQLException;

    ModelType save(ModelType obj) throws SQLException;

    ModelType findById(PKType key) throws SQLException;
    List<ModelType> findAll() throws SQLException;

    boolean delete(PKType key) throws SQLException;
    boolean deleteOne(ModelType obj) throws SQLException;
    boolean deleteAll() throws SQLException;

}
