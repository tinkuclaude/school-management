package com.guimotech.dao.repos;


import com.guimotech.config.DBConfig;
import com.guimotech.dao.model.Enseignant;
import com.guimotech.dao.repos.inter.RepoAbs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnseignantRepo extends RepoAbs<Enseignant, String> {
    private static DBConfig dbConfig = DBConfig.getInstance();
    private static EnseignantRepo instance = null;
    public static EnseignantRepo getInstance() {
        if(instance == null) {
            instance = new EnseignantRepo();
        }
        return instance;
    }

    private EnseignantRepo() {
        super();
    }

    @Override
    public boolean create(Enseignant obj) throws SQLException {
        String sql = "INSERT into Enseignant (matricule, nom, prenom, civilite, date_naiss, telephone) " +
                " values (?, ?, ?, ?, ?, ?) returning id;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, obj.getMatricule());
        ps.setString(2, obj.getNom());
        ps.setString(3, obj.getPrenom());
        ps.setInt(4, obj.getCivilite());
        ps.setDate(5, obj.getDatenaiss());
        ps.setString(6, obj.getTelephone());

        ResultSet res = ps.executeQuery();
        if(res != null && res.next()) {
            obj.setId(res.getLong("id"));
        }
        return obj.getId() != null;
    }

    @Override
    public boolean update(Enseignant obj) throws SQLException {
        String sql = "UPDATE enseignant set matricule = ?, nom = ?, prenom = ?, civilite = ?, date_naiss = ?, telephone = ? where id = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, obj.getMatricule());
        ps.setString(2, obj.getNom());
        ps.setString(3, obj.getPrenom());
        ps.setInt(4, obj.getCivilite());
        ps.setDate(5, obj.getDatenaiss());
        ps.setString(6, obj.getTelephone());
        ps.setLong(7, obj.getId());

        return ps.executeUpdate() >= 0;

    }

    @Override
    public Enseignant save(Enseignant obj) throws SQLException {

        if(obj.getId() == null) {
            create(obj);
        }
        else {
            update(obj);
        }
        return obj;
    }

    @Override
    public Enseignant findById(String key) throws SQLException {

        Enseignant enseignant = new Enseignant();

        String sql = "SELECT * FROM ENSEIGNANT WHERE MATRICULE = ?;";
        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, key);

        ResultSet res = ps.executeQuery();
        if(res != null && res.next()) {
            enseignant = getDataModel(res);
        }

        return enseignant;
    }

    public Enseignant findById(Long key) throws SQLException {

        Enseignant enseignant = new Enseignant();

        String sql = "SELECT * FROM ENSEIGNANT WHERE ID = ?;";
        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setLong(1, key);

        ResultSet res = ps.executeQuery();
        if(res != null && res.next()) {
            enseignant = getDataModel(res);
        }

        return enseignant;
    }

    private Enseignant getDataModel(ResultSet res) throws SQLException {
        Enseignant enseignant = new Enseignant();

        enseignant.setId(res.getLong("id"));
        enseignant.setMatricule(res.getString("matricule"));
        enseignant.setNom(res.getString("nom"));
        enseignant.setPrenom(res.getString("prenom"));
        enseignant.setCivilite(res.getInt("civilite"));
        enseignant.setDatenaiss(res.getDate("date_naiss"));
        enseignant.setTelephone(res.getString("telephone"));

        return enseignant;
    }

    @Override
    public List<Enseignant> findAll() throws SQLException {

        List<Enseignant> enseignants = new ArrayList<>();

        String sql = "SELECT * FROM ENSEIGNANT ;";
        Statement ps = dbConfig.getConnection().createStatement();

        ResultSet res = ps.executeQuery(sql);
        if(res != null) {
            while(res.next()) {
                enseignants.add(getDataModel(res));
            }
        }

        return enseignants;
    }

    @Override
    public boolean delete(String key) throws SQLException {
        String sql = "DELETE FROM Enseignant where matricule = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setString(1, key);

        return ps.executeUpdate() > 0;
    }

    public boolean delete(Long key) throws SQLException {
        String sql = "DELETE FROM Enseignant where id = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setLong(1, key);

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean deleteOne(Enseignant obj) throws SQLException {
        return delete(obj.getMatricule());
    }

    @Override
    public boolean deleteAll() throws SQLException {
        String sql = "DELETE FROM Enseignant ;";

        Statement ps = dbConfig.getConnection().createStatement();

        return ps.executeUpdate(sql) >= 0;
    }
}
