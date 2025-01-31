package com.guimotech.dao.repos.inter;

import com.guimotech.dao.model.Trimestre;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class RepoAbs <ModelType, PKType>{

    public RepoAbs() {}

//    boolean create(ModelType obj) throws SQLException{
//        return true;
//    }

    public abstract boolean create(ModelType obj) throws SQLException;

    public abstract boolean update(ModelType obj) throws SQLException;

    public abstract  ModelType save(ModelType obj) throws SQLException;

    public abstract ModelType findById(PKType key) throws SQLException;
    public abstract List<ModelType> findAll() throws SQLException;

    public abstract boolean delete(PKType key) throws SQLException;
    public abstract boolean deleteOne(ModelType obj) throws SQLException;
    public abstract  boolean deleteAll() throws SQLException;

    public List<HashMap<String, Object>> getAll(ResultSet res) throws SQLException {
        ResultSetMetaData resMetaDate = res.getMetaData();
        List<HashMap<String, Object>> data = new ArrayList<>();

        HashMap<String, Object> colMap = new HashMap<>();
        int count = resMetaDate.getColumnCount();

        for(int i=0; i< count; i++){
            colMap.put(String.valueOf(i), resMetaDate.getColumnName(i+1));
        }
        data.add(colMap);

        while (res.next()) {
            HashMap<String, Object> line = new HashMap<>();
            for(int i=0; i< count; i++){
                line.put(resMetaDate.getColumnName(i+1), res.getObject(resMetaDate.getColumnName(i+1)));
            }
            data.add(line);
        }

        return data;
    }
}
