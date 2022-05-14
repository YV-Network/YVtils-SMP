package yv.tils.smp.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.LanguageSystem.LanguagePlaceholder;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(args.length == 0) {
            sendUsage(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "adventure", "2", "a" -> {
                if (player.getGameMode() == GameMode.ADVENTURE) {
                    player.sendMessage(ChatColor.RED + "You are already in this Gamemode!");
                } else {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                    player.sendMessage(ChatColor.GRAY + "Your Gamemode has been changed to " + ChatColor.GREEN +  "ADVENTURE");
                }
            }
            case "survival", "0", "su" -> {
                if (player.getGameMode() == GameMode.SURVIVAL) {
                    player.sendMessage(ChatColor.RED + "You are already in this Gamemode!");
                } else {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                    player.sendMessage(ChatColor.GRAY + "Your Gamemode has been changed to " + ChatColor.GREEN +  "SURVIVAL");
                }
            }
            case "creative", "1", "c" -> {
                if (player.getGameMode() == GameMode.CREATIVE) {
                    player.sendMessage(ChatColor.RED + "You are already in this Gamemode!");
                } else {
                    player.setGameMode(GameMode.CREATIVE);
                    player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                    player.sendMessage(ChatColor.GRAY + "Your Gamemode has been changed to " + ChatColor.GREEN +  "CREATIVE");
                }
            }
            case "spectator", "3", "sp" -> {
                if (player.getGameMode() == GameMode.SPECTATOR) {
                    player.sendMessage(ChatColor.RED + "You are already in this Gamemode!");
                } else {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                    player.sendMessage(ChatColor.GRAY + "Your Gamemode has been changed to " + ChatColor.GREEN +  "SPECTATOR");
                }
            }
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguagePlaceholder.CommandUsage() + ChatColor.BLUE +
                "/gm <0,1,2,3 / survival, creative, adventure, spectator / su, c, a, sp>");
    }
}
