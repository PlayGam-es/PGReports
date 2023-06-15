package dev.jay.pgreports.Reports.Commands.Sub;

import dev.jay.pgreports.PGReports;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class report_player{


    private final PGReports plugin;
    public report_player(PGReports plugin){
        this.plugin = plugin;
    }

    public void report_a_player(Player player, Player target, String reason) throws SQLException {

        if (player.hasPermission("pgreports.player.report")){

            plugin.query.reportPlayer(player, target, reason);

        }
    }
}
