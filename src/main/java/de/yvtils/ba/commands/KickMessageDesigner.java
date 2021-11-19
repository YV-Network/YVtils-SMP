package de.yvtils.ba.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickMessageDesigner implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (Player player : Bukkit.getOnlinePlayers()) {

            player.kickPlayer(ChatColor.BLUE + "« " + ChatColor.AQUA + "YV Network" + ChatColor.BLUE +" »");

        }
        return false;
    }
}
