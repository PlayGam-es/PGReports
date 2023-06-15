package dev.jay.pgreports.database;

import dev.jay.pgreports.PGReports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class db {

    private final PGReports plugin;
    public db(PGReports plugin){
        this.plugin = plugin;
    }

    static Connection connection;

    public void InitDb() throws SQLException {

        if (!Objects.equals(plugin.Datatype, "MySQL")) {

            try {
                Class.forName ("org.h2.Driver");
                connection = DriverManager.getConnection ("jdbc:h2:" + plugin.getDataFolder().getAbsolutePath() + "/data/database");
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }else{
            String hostname = plugin.getConfig().getString("Database.Address");
            String port = plugin.getConfig().getString("Database.Port");
            String username = plugin.getConfig().getString("Database.Username");
            String password = plugin.getConfig().getString("Database.Password");
            String dbName = plugin.getConfig().getString("Database.DatabaseName");
            String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?autoReconnect=true";

            connection = DriverManager.getConnection(url, username, password);


        }

    }

    public void CloseDb() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Connection GetDb() throws SQLException {
        try {
            String sql = "SELECT VERSION();";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.executeQuery();
        } catch(Exception e) {
            InitDb();
        }
        return connection;
    }

}
