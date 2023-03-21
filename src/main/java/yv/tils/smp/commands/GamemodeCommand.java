package yv.tils.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.7
 */
public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sendUsage(sender);
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "adventure", "2", "a" -> {
                    if (player.getGameMode() == GameMode.ADVENTURE) {
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE));
                    } else {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ADVENTURE));
                    }
                }
                case "survival", "0", "su" -> {
                    if (player.getGameMode() == GameMode.SURVIVAL) {
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE));
                    } else {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_SURVIVAL));
                    }
                }
                case "creative", "1", "c" -> {
                    if (player.getGameMode() == GameMode.CREATIVE) {
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE));
                    } else {
                        player.setGameMode(GameMode.CREATIVE);
                        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_CREATIVE));
                    }
                }
                case "spectator", "3", "sp" -> {
                    if (player.getGameMode() == GameMode.SPECTATOR) {
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE));
                    } else {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_SPECTATOR));
                    }
                }
            }
        }else if (args.length == 2) {
            player = Bukkit.getPlayer(args[1]);

            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("PLAYER");
            list2.add(player.getName());

            switch (args[0].toLowerCase()) {
                case "adventure", "2", "a" -> {
                    if (player.getGameMode() == GameMode.ADVENTURE) {
                        sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE_OTHER), list1, list2));
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE));
                    } else {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                        sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ADVENTURE_OTHER), list1, list2));
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ADVENTURE));
                    }
                }
                case "survival", "0", "su" -> {
                    if (player.getGameMode() == GameMode.SURVIVAL) {
                        sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE_OTHER), list1, list2));
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE));
                    } else {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                        sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_SURVIVAL_OTHER), list1, list2));
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_SURVIVAL));
                    }
                }
                case "creative", "1", "c" -> {
                    if (player.getGameMode() == GameMode.CREATIVE) {
                        sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE_OTHER), list1, list2));
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE));
                    } else {
                        player.setGameMode(GameMode.CREATIVE);
                        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                        sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_CREATIVE_OTHER), list1, list2));
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_CREATIVE));
                    }
                }
                case "spectator", "3", "sp" -> {
                    if (player.getGameMode() == GameMode.SPECTATOR) {
                        sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE_OTHER), list1, list2));
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE));
                    } else {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                        sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_SPECTATOR_OTHER), list1, list2));
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_SPECTATOR));
                    }
                }
            }
        }


        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/gm <0,1,2,3 / survival, creative, adventure, spectator / su, c, a, sp> [player]");
    }
}
