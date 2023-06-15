package dev.jay.pgreports.database;

import dev.jay.pgreports.PGReports;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class dbq {

    private final PGReports plugin;
    public dbq(PGReports plugin){
        this.plugin = plugin;
    }


    public void createTable() throws SQLException, SQLException {
        PreparedStatement table = plugin.con.GetDb().prepareStatement("CREATE TABLE IF NOT EXISTS dicegame(playerUUID varchar, playerName varchar, number float, PRIMARY KEY(playerUUID))");
        table.executeUpdate();
        plugin.getLogger().info(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_GREEN + "DiceGame" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + "Database launched correctly and is all set.");
    }

    public void createPlayer(Player player) throws SQLException {
        UUID uuid = player.getUniqueId();
        if (!doesPlayerExist(player)){
            PreparedStatement createPlayer = plugin.con.GetDb().prepareStatement("INSERT INTO dicegame(playerUUID,playerName,number) VALUES (?,?,?)");
            createPlayer.setString(1, uuid.toString());
            createPlayer.setString(2, player.getName());
            createPlayer.setFloat(3, plugin.getConfig().getInt("Game.Max"));
            createPlayer.executeUpdate();
        }
    }


    public boolean doesPlayerExist(Player player) throws SQLException{
        UUID uuid = player.getUniqueId();
        PreparedStatement ps1 = plugin.con.GetDb().prepareStatement("SELECT * FROM dicegame WHERE playerUUID=?");
        ps1.setString(1, String.valueOf(uuid));
        ResultSet rs1 = ps1.executeQuery();
        if (rs1.next()){
            return true;
        }else{
            return false;
        }
    }

}
