package com.guimotech.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static String JDBC_DRIVE = "jdbc:postgresql://";
    private static String DRIVE_CASS_NAME = "org.postgresql.Driver";

    private String address;
    private String port;
    private String dbName;

    private String user;
    private String password;

    private Connection connection = null;


    private static DBConfig instance = null;
    public static DBConfig getInstance() {
        if(instance == null) {
            instance = new DBConfig();
        }
        return instance;
    }

    private DBConfig() {
    }

    public Connection openConnection(String address, String port, String dbName,
                                     String user, String password)  {
        // charge le drive permettant de se connecter a la BD
        try {
            Class.forName(DRIVE_CASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return  null;
        }

        String url = JDBC_DRIVE+address+":"+port+"/"+dbName;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        this.address = address;
        this.port = port;
        this.dbName = dbName;
        this.user = user;
        this.password = password;

        return connection;
    }

    private Connection reopenConnection() {
        try {
            Class.forName(DRIVE_CASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return  null;
        }

        String url = JDBC_DRIVE+address+":"+port;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return connection;
    }

    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) {
            return reopenConnection();
        }
        return connection;
    }

    public void close() throws SQLException{
        if(connection == null || connection.isClosed()) {
            connection = null;
        } else {
            connection.close();
            connection = null;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
