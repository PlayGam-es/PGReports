package dev.jay.pgreports.database;

import dev.jay.pgreports.PGReports;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbq {

    private final PGReports plugin;
    public dbq(PGReports plugin){
        this.plugin = plugin;
    }


    public void createTable() throws SQLException, SQLException {
        PreparedStatement table = plugin.con.GetDb().prepareStatement("CREATE TABLE IF NOT EXISTS reports(reporterName varchar, reportedName varchar, reason varchar ,PRIMARY KEY(reportedName))");
        PreparedStatement table2 = plugin.con.GetDb().prepareStatement("CREATE TABLE IF NOT EXISTS staffmode(playerUUID varchar, Location varchar,PRIMARY KEY(playerUUID))");
        PreparedStatement table3 = plugin.con.GetDb().prepareStatement("CREATE TABLE IF NOT EXISTS pastreports(reportID integer, reporterName varchar, reportedName varchar, reason varchar ,PRIMARY KEY(reportID))");
        table.executeUpdate();
        table2.executeUpdate();
        table3.executeUpdate();
        plugin.getLogger().info(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_GREEN + "Reports" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + "Database launched correctly and is all set.");
    }

    public void reportPlayer(Player player, Player target, String reason) throws SQLException {
        if (!doesReportExist(player, target)){
            PreparedStatement createPlayer = plugin.con.GetDb().prepareStatement("INSERT INTO reports(reporterName,reportedName,reason) VALUES (?,?,?)");
            createPlayer.setString(1, player.getName());
            createPlayer.setString(2, target.getName());
            createPlayer.setString(3, reason);
            createPlayer.executeUpdate();
        }
    }


    public boolean doesReportExist(Player player, Player target) throws SQLException{
        PreparedStatement ps1 = plugin.con.GetDb().prepareStatement("SELECT reportedName FROM reports WHERE reportedName=?");
        ps1.setString(1, target.getName());
        ResultSet rs1 = ps1.executeQuery();
        if (rs1.next()){
            player.sendMessage("Hey, a report already exists for this player...");
            return true;
        }else{
            return false;
        }
    }



}
