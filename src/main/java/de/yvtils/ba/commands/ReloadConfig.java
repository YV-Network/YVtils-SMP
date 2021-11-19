package de.yvtils.ba.commands;

import de.yvtils.ba.Main;
import de.yvtils.ba.Placeholder.MessagePlaceholder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfig implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.hasPermission("yvtils.ba.command.reloadconfig")) {
            sender.sendMessage(MessagePlaceholder.PREFIXERELOAD + ChatColor.RED + " Config Reload started!");
            Main.getInstance().reloadConfig();
            sender.sendMessage(  MessagePlaceholder.PREFIXERELOAD + ChatColor.GREEN + " Config Reload completed!");
        }
        return true;
    }
}
