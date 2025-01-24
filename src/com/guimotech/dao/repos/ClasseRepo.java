package com.guimotech.dao.repos;

import com.guimotech.config.DBConfig;
import com.guimotech.dao.model.Classe;
import com.guimotech.dao.model.Niveau;
import com.guimotech.dao.repos.inter.RepoAbs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClasseRepo extends RepoAbs<Classe, String> {
    private static DBConfig dbConfig = DBConfig.getInstance();
    private static ClasseRepo instance = null;
    public static ClasseRepo getInstance() {
        if(instance == null) {
            instance = new ClasseRepo();
        }
        return instance;
    }

    private ClasseRepo() {
        super();
    }

    @Override
    public boolean create(Classe obj) throws SQLException {
        String sql = "INSERT into classe (code, intitule, niveau) values (?, ?,?) returning id ;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
//        ps.setId(1, obj.getId());
        ps.setString(1, obj.getCode());
        ps.setString(2, obj.getIntitule());
        ps.setString(3, obj.getNiveau());

        ResultSet res = ps.executeQuery();
        if(res != null && res.next()) {
            obj.setId(res.getLong("id"));
        } else {
            obj.setId(null);
        }

        return obj.getId() != null;
    }



    @Override
    public boolean update(Classe obj) throws SQLException {
        String sql = "UPDATE classe set code = ?, intitule=?, niveau=? where id = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, obj.getCode());
        ps.setString(2, obj.getIntitule());
        ps.setString(3, obj.getNiveau());
        ps.setLong(4, obj.getId());

        return ps.executeUpdate() >= 0;

    }

    @Override
    public Classe save(Classe obj) throws SQLException{
//        Claase classe = findById(obj.getId());
        if(obj.getId() == null) {
            create(obj);
        }
        else {
            update(obj);
        }
        return obj;
    }


    @Override
    public Classe findById(String code) throws SQLException {


        String sql = "SELECT * FROM CLASSE WHERE CODE = ?;";
        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, code);

        ResultSet res = ps.executeQuery();
        return  getDataModel(res);
    }

    public Classe findById(Long id) throws SQLException {

        String sql = "SELECT * FROM CLASSE WHERE ID = ?;";
        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setLong(1, id);

        ResultSet res = ps.executeQuery();
        return  getDataModel(res);
    }

    private Classe getDataModel(ResultSet res) throws SQLException {

        if(res != null) {
            if(res.next()) {
                return getData(res);
            }
        }
        return new Classe();
    }

    private Classe getData(ResultSet res) throws SQLException {
        Classe classe = new Classe();
        classe.setId(res.getLong("id"));
        classe.setCode(res.getString("code"));
        classe.setIntitule(res.getString("intitule"));
        classe.setNiveau(res.getString("niveau"));
        return classe;
    }

    @Override
    public List<Classe> findAll() throws SQLException {

        List<Classe> classes = new ArrayList<>();

        String sql = "SELECT * FROM CLASSE ;";
        Statement ps = dbConfig.getConnection().createStatement();

        ResultSet res = ps.executeQuery(sql);
        if(res != null) {
            while(res.next()) {
                classes.add(getData(res));
            }
        }

        return classes;
    }

//    @Override
//    public boolean delete(String key) throws SQLException {
//        return false;
//    }

    @Override
    public boolean delete(String key) throws SQLException {
        String sql = "DELETE FROM Classe where code = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, key);

        return ps.executeUpdate() > 0;
    }

    public boolean delete(Long key) throws SQLException {
        String sql = "DELETE FROM Classe where id = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setLong(1, key);

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean deleteOne(Classe obj) throws SQLException {
        return delete(obj.getId());
    }

    @Override
    public boolean deleteAll() throws SQLException {
        String sql = "DELETE FROM Classe ;";

        Statement ps = dbConfig.getConnection().createStatement();

        return ps.executeUpdate(sql) >= 0;
    }
}





