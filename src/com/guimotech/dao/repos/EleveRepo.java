package com.guimotech.dao.repos;

import com.guimotech.config.DBConfig;
import com.guimotech.dao.model.Eleve;
import com.guimotech.dao.repos.inter.RepoAbs;
import com.guimotech.dao.repos.inter.RepoAbsEleve;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EleveRepo extends RepoAbsEleve<Eleve, String> {
    private static DBConfig dbConfig = DBConfig.getInstance();
    private static EleveRepo instance = null;
    public static EleveRepo getInstance() {
        if(instance == null) {
            instance = new EleveRepo();
        }
        return instance;
    }

    private EleveRepo() {
        super();
    }

    @Override
    public boolean create(Eleve obj) throws SQLException {
        String sql = "INSERT into Eleve (matricule, nom, prenom, sexe, datenaiss) values (?, ?, ?, ?, ?);";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, obj.getMatricule());
        ps.setString(2, obj.getNom());
        ps.setString(3, obj.getPrenom());
        ps.setString(4, obj.getSexe());
        ps.setString(5, obj.getDatenaiss());

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean update(Eleve obj) throws SQLException {
        String sql = "UPDATE eleve set nom = ? where matricule = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, obj.getMatricule());
        ps.setString(2, obj.getNom());
        ps.setString(3, obj.getPrenom());
        ps.setString(4, obj.getSexe());
        ps.setString(5, obj.getDatenaiss());

        return ps.executeUpdate() >= 0;

    }

    @Override
    public Eleve save(Eleve obj) throws SQLException {
        Eleve eleve = findById(obj.getMatricule());
        if(eleve.getMatricule() == null) {
            create(obj);
//            trim = findById(obj.getNumero());
        }
        else {
            update(obj);
        }
       eleve = obj;

        return eleve;
    }

    @Override
    public Eleve findById(String key) throws SQLException {

        Eleve eleve = new Eleve();

        String sql = "SELECT * FROM ELEVE WHERE MATRICULE = ?;";
        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, key);

        ResultSet res = ps.executeQuery();
        if(res != null) {
            if(res.next()) {
                eleve.setMatricule(res.getString("matricule"));
                eleve.setNom(res.getString("nom"));
                eleve.setPrenom(res.getString("prenom"));
                eleve.setSexe(res.getString("sexe"));
                eleve.setDatenaiss(res.getString("datnaiss"));
            }
        }

        return eleve;
    }

    @Override
    public List<Eleve> findAll() throws SQLException {

        List<Eleve> eleves = new ArrayList<>();

        String sql = "SELECT * FROM ELEVE ;";
        Statement ps = dbConfig.getConnection().createStatement();

        ResultSet res = ps.executeQuery(sql);
        if(res != null) {
            while(res.next()) {
                Eleve eleve = new Eleve();
                eleve.setMatricule(res.getString("matricule"));
                eleve.setNom(res.getString("nom"));
                eleve.setPrenom(res.getString("prenom"));
                eleve.setSexe(res.getString("sexe"));
                eleve.setDatenaiss(res.getString("datenaiss"));
                eleves.add(eleve);
            }
        }

        return eleves;
    }

    @Override
    public boolean delete(String key) throws SQLException {
        String sql = "DELETE FROM Eleve where matricule = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, key);

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean deleteOne(Eleve obj) throws SQLException {
        return delete(obj.getMatricule());
    }

    @Override
    public boolean deleteAll() throws SQLException {
        String sql = "DELETE FROM Eleve ;";

        Statement ps = dbConfig.getConnection().createStatement();

        return ps.executeUpdate(sql) > 0;
    }
}
