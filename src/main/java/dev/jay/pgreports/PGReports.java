package dev.jay.pgreports;

import dev.jay.pgreports.database.db;
import dev.jay.pgreports.database.dbq;
import org.bukkit.plugin.java.JavaPlugin;

public final class PGReports extends JavaPlugin {

    public String Datatype;
    public db con;
    public dbq query;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Datatype = getConfig().getString("Plugin.Database.Type");
        con = new db(this);
        query = new dbq(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
