package yv.tils.smp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            player.setHealth(20);
            player.setFoodLevel(20);
            player.sendTitle(ChatColor.GRAY + "You got", ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "HEALED", 20, 50, 20);
        }
        return false;
    }
}
