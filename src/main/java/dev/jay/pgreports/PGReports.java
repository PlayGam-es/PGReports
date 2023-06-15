package dev.jay.pgreports;

import dev.jay.pgreports.Reports.Commands.Sub.report_player;
import dev.jay.pgreports.Reports.Commands.mainCommand;
import dev.jay.pgreports.database.db;
import dev.jay.pgreports.database.dbq;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class PGReports extends JavaPlugin {

    public String Datatype;
    public db con;
    public dbq query;
    private report_player reportPlayer;

    @Override
    public void onEnable() {

        Datatype = getConfig().getString("Plugin.Database.Type");
        con = new db(this);
        query = new dbq(this);
        reportPlayer = new report_player(this);
        try {
            con.InitDb();
            if (con.GetDb() != null){
                query.createTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getCommand("report").setExecutor(new mainCommand(this));
    }

    @Override
    public void onDisable() {

        con.CloseDb();

    }

    public report_player getReportPlayer(){
        return reportPlayer;
    }
}
