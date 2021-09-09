package sv.com.devskodigo.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DbConnection {

    private final String JDBC_DRIVER;
    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PASS;

    public DbConnection() {
        Properties properties = new Properties();
        String filePath = "./src/main/java/sv/com/devskodigo/model/dao/flights.properties";
        try {
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        DB_URL = properties.getProperty("DB_URL");
        DB_USER = properties.getProperty("DB_USERNAME");
        DB_PASS = properties.getProperty("DB_PASSWORD");
    }

    private void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: Something went wrong with JDBC driver.");
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        registerDriver();
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}