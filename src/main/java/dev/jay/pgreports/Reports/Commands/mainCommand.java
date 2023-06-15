package dev.jay.pgreports.Reports.Commands;

import dev.jay.pgreports.PGReports;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class mainCommand implements CommandExecutor {

    private final PGReports plugin;
    public mainCommand(PGReports plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("report")) {
            if (sender instanceof Player) {

                Player player = (Player) sender;
                Player target = Bukkit.getPlayer(args[0]);

                String reason = "";
                for (int i = 1; i < args.length; i++) {
                    reason = reason + args[i] + " ";
                }

                try {
                    plugin.getReportPlayer().report_a_player(player, target, reason);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }

        }


        return true;
    }
}