package com.guimotech.dao.repos;

import com.guimotech.config.DBConfig;
import com.guimotech.dao.model.Niveau;
import com.guimotech.dao.model.Trimestre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NiveauRepo {
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


    public boolean create(Niveau obj) throws SQLException
    {
        String sql = "INSERT into Niveau (code, intitule, frais_insciption) values (?, ?,?);";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, obj.getCode());
        ps.setString(2, obj.getIntitule());
        ps.setInt(3, obj.getFrais_inscription());


        return ps.executeUpdate() > 0;
    }

    public boolean update(Niveau obj) throws SQLException {
        String sql = "UPDATE niveau set intitule = ? where id = ?;";
        String sgl="UPDATE niveau set code= ? where  id= ? ";
        sgl = "UPDATE niveau set frais_inscription where id= ?";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setLong(2,obj.getId());
        ps.setString(2, obj.getCode());

        ps.setString(1, obj.getIntitule());

        ps.setInt(2,obj.getFrais_insciption());


        return ps.executeUpdate() >= 0;

    }


    public Niveau save(Niveau obj) throws SQLException {
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
            }
        }

        return niveau;
    }


    public List<Niveau> findAll() throws SQLException {

        List<Niveau> niveaux = new ArrayList<>();

        String sql = "SELECT * FROM NIVEAU ;";
        Statement ps = dbConfig.getConnection().createStatement();

        ResultSet res = ps.executeQuery(sql);
        if(res != null) {
            while(res.next()) {
                Niveau niveau = new Niveau();
//                niveau.setId(res.getLong(("id")));
                niveau.setCode(res.getString("code"));
                niveau.setIntitule(res.getString("intitule"));
                niveau.setFrais_insciption(res.getInt("frais_inscription"));
                niveaux.add(niveau);
            }
        }

        return niveaux;
    }


    public boolean delete(String key) throws SQLException {
        String sql = "DELETE FROM NIVEAU where code = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, key);

        return ps.executeUpdate() > 0;
    }


    public boolean deleteOne(Trimestre obj) throws SQLException {
        return delete(obj.getCode());
    }


    public boolean deleteAll() throws SQLException {
        String sql = "DELETE FROM Niveau ;";

        Statement ps = dbConfig.getConnection().createStatement();

        return ps.executeUpdate(sql) > 0;
    }
}
