package com.guimotech.dao.repos;

import com.guimotech.config.DBConfig;
import com.guimotech.dao.model.Niveau;
import com.guimotech.dao.model.Trimestre;
import com.guimotech.dao.repos.inter.RepoAbs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NiveauRepo extends RepoAbs<Niveau, String> {
    private static DBConfig dbConfig = DBConfig.getInstance();
    private static NiveauRepo instance = null;
    public static NiveauRepo getInstance() {
        if(instance == null) {
            instance = new NiveauRepo();
        }
        return instance;
    }

    private NiveauRepo() {
        super();
    }

    @Override
    public boolean create(Niveau obj) throws SQLException {
        String sql = "INSERT into niveau (code, intitule, frais-inscription) values (?, ?,?);";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
//        ps.setId(1, obj.getId());
        ps.setString(1, obj.getCode());
        ps.setString(2, obj.getIntitule());
        ps.setInt(3, obj.getFrais_inscription());


        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean update(Niveau obj) throws SQLException {
        String sql = "UPDATE Niveau set code = ? intitule=? frais-inscription where code = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, obj.getCode());
        ps.setString(2, obj.getIntitule());
        ps.setInt(3, obj.getFrais_insciption());

        return ps.executeUpdate() >= 0;

    }

    @Override
    public Niveau save(Niveau obj) throws SQLException{
        Niveau niveau = findById(obj.getCode());
        if(niveau.getCode() == null) {
            create(obj);
//            niveau = findById(obj.getCode());
        }
        else {
            update(obj);
        }
        niveau = obj;

        return niveau;
    }

//    @Override
//    public Niveau findById(String key) throws SQLException {
//        return null;
//    }


    @Override
    public Niveau findById(String key) throws SQLException {

       Niveau niveau = new Niveau();

        String sql = "SELECT * FROM NIVEAU WHERE CODE = ?;";
        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, key);

        ResultSet res = ps.executeQuery();
        if(res != null) {
            if(res.next()) {
                niveau.setCode(res.getString("code"));
                niveau.setIntitule(res.getString("intitule"));
                niveau.setFrais_inscription(res.getInt("frais_inscription"));

            }
        }

        return niveau;
    }

    @Override
    public List<Niveau> findAll() throws SQLException {

        List<Niveau> niveaux = new ArrayList<>();

        String sql = "SELECT * FROM NIVEAU ;";
        Statement ps = dbConfig.getConnection().createStatement();

        ResultSet res = ps.executeQuery(sql);
        if(res != null) {
            while(res.next()) {
                Niveau niveau = new Niveau();
                niveau.setCode(res.getString("code"));
                niveau.setIntitule(res.getString("intitule"));
                niveau.setFrais_insciption(res.getInt("frais_inscrption"));
                niveaux.add(niveau);
            }
        }

        return niveaux;
    }

//    @Override
//    public boolean delete(String key) throws SQLException {
//        return false;
//    }

    @Override
    public boolean delete(String key) throws SQLException {
        String sql = "DELETE FROM Niveau where code = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, key);

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean deleteOne(Niveau obj) throws SQLException {
        return delete(obj.getCode());
    }

    @Override
    public boolean deleteAll() throws SQLException {
        String sql = "DELETE FROM Niveau ;";

        Statement ps = dbConfig.getConnection().createStatement();

        return ps.executeUpdate(sql) > 0;
    }
}


