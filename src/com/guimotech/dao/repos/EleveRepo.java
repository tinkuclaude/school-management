package com.guimotech.dao.repos;

import com.guimotech.config.DBConfig;
import com.guimotech.dao.model.Eleve;
import com.guimotech.dao.repos.inter.RepoAbs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EleveRepo extends RepoAbs<Eleve, String> {
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
        String sql = "INSERT into Eleve (matricule, nom, prenom, sexe, date_naiss) " +
                " values (?, ?, ?, ?, ?) returning id;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, obj.getMatricule());
        ps.setString(2, obj.getNom());
        ps.setString(3, obj.getPrenom());
        ps.setObject(4, obj.getSexe());
        ps.setDate(5, obj.getDatenaiss());

        ResultSet res = ps.executeQuery();
        if(res != null && res.next()) {
            obj.setId(res.getLong("id"));
        }
        return obj.getId() != null;
    }

    @Override
    public boolean update(Eleve obj) throws SQLException {
        String sql = "UPDATE eleve set matricule = ?, nom = ?, prenom = ?, sexe = ?, date_naiss = ? where id = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, obj.getMatricule());
        ps.setString(2, obj.getNom());
        ps.setString(3, obj.getPrenom());
        ps.setObject(4, obj.getSexe());
        ps.setDate(5, obj.getDatenaiss());
        ps.setLong(6, obj.getId());

        return ps.executeUpdate() >= 0;

    }

    @Override
    public Eleve save(Eleve obj) throws SQLException {

        if(obj.getId() == null) {
            create(obj);
        }
        else {
            update(obj);
        }
        return obj;
    }

    @Override
    public Eleve findById(String key) throws SQLException {

        Eleve eleve = new Eleve();

        String sql = "SELECT * FROM ELEVE WHERE MATRICULE = ?;";
        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, key);

        ResultSet res = ps.executeQuery();
        if(res != null && res.next()) {
            eleve = getDataModel(res);
        }

        return eleve;
    }

    public Eleve findById(Long key) throws SQLException {

        Eleve eleve = new Eleve();

        String sql = "SELECT * FROM ELEVE WHERE ID = ?;";
        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setLong(1, key);

        ResultSet res = ps.executeQuery();
        if(res != null && res.next()) {
            eleve = getDataModel(res);
        }

        return eleve;
    }

    private Eleve getDataModel(ResultSet res) throws SQLException {
        Eleve eleve = new Eleve();

        eleve.setId(res.getLong("id"));
        eleve.setMatricule(res.getString("matricule"));
        eleve.setNom(res.getString("nom"));
        eleve.setPrenom(res.getString("prenom"));
        eleve.setSexe((Integer) res.getObject("sexe"));
        eleve.setDatenaiss(res.getDate("date_naiss"));

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
                eleves.add(getDataModel(res));
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

    public boolean delete(Long key) throws SQLException {
        String sql = "DELETE FROM Eleve where id = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setLong(1, key);

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

        return ps.executeUpdate(sql) >= 0;
    }
}
