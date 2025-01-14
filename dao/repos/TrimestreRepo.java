package com.guimotech.dao.repos;

import com.guimotech.config.DBConfig;
import com.guimotech.dao.model.Trimestre;
import com.guimotech.dao.repos.inter.RepoAbs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrimestreRepo extends RepoAbs<Trimestre, Integer> {

    private static DBConfig dbConfig = DBConfig.getInstance();
    private static TrimestreRepo instance = null;
    public static TrimestreRepo getInstance() {
        if(instance == null) {

            instance = new TrimestreRepo();
        }
        return instance;
    }

    private TrimestreRepo() {
        super();
    }

    @Override
    public boolean create(Trimestre obj) throws SQLException {
        String sql = "INSERT into Trimestre (numero, intitule) values (?, ?);";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setInt(1, obj.getNumero());
        ps.setString(2, obj.getIntitule());

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean update(Trimestre obj) throws SQLException {
        String sql = "UPDATE Trimestre set intitule = ? where numero = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setInt(2, obj.getNumero());
        ps.setString(1, obj.getIntitule());

        return ps.executeUpdate() >= 0;

    }

    @Override
    public Trimestre save(Trimestre obj) throws SQLException {
        Trimestre trim = findById(obj.getNumero());
        if(trim.getNumero() == null) {
            create(obj);
//            trim = findById(obj.getNumero());
        }
        else {
            update(obj);
        }
        trim = obj;

        return trim;
    }

    @Override
    public Trimestre findById(Integer key) throws SQLException {

        Trimestre trim = new Trimestre();

        String sql = "SELECT * FROM TRIMESTRE WHERE NUMERO = ?;";
        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setInt(1, key);

        ResultSet res = ps.executeQuery();
        if(res != null) {
            if(res.next()) {
                trim.setNumero(res.getInt("numero"));
                trim.setIntitule(res.getString("intitule"));
            }
        }

        return trim;
    }

    @Override
    public List<Trimestre> findAll() throws SQLException {

        List<Trimestre> trims = new ArrayList<>();

        String sql = "SELECT * FROM TRIMESTRE ;";
        Statement ps = dbConfig.getConnection().createStatement();

        ResultSet res = ps.executeQuery(sql);
        if(res != null) {
            while(res.next()) {
                Trimestre trim = new Trimestre();
                trim.setNumero(res.getInt("numero"));
                trim.setIntitule(res.getString("intitule"));
                trims.add(trim);
            }
        }

        return trims;
    }

    @Override
    public boolean delete(Integer key) throws SQLException {
        String sql = "DELETE FROM Trimestre where numero = ?;";

        PreparedStatement ps = dbConfig.getConnection().prepareStatement(sql);
        ps.setInt(1, key);

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean deleteOne(Trimestre obj) throws SQLException {
        return delete(obj.getNumero());
    }

    @Override
    public boolean deleteAll() throws SQLException {
        String sql = "DELETE FROM Trimestre ;";

        Statement ps = dbConfig.getConnection().createStatement();

        return ps.executeUpdate(sql) > 0;
    }
}
