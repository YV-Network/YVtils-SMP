package yv.tils.smp.manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.internalapi.StringReplacer;

import java.util.ArrayList;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class Gamemode implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("yvtils.command.gamemode")) return false;
        if (!(sender instanceof Player) && args.length != 2) {
            sender.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_ARGUMENT_MISSING));
            return false;
        }

        switch (args.length) {
            case 1 -> {
                Player player = (Player) sender;

                switch (args[0].toLowerCase()) {
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
                    case "adventure", "2", "a" -> {
                        if (player.getGameMode() == GameMode.ADVENTURE) {
                            player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ALREADY_IN_THIS_GAMEMODE));
                        } else {
                            player.setGameMode(GameMode.ADVENTURE);
                            player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 15, 15);
                            player.sendMessage(LanguageFile.getMessage(LanguageMessage.GAMEMODE_SWITCH_ADVENTURE));
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
                    default -> sendUsage(sender);
                }
            }
            case 2 -> {
                Player player = Bukkit.getPlayer(args[1]);

                List<String> list1 = new ArrayList();
                List<String> list2 = new ArrayList();
                list1.add("PLAYER");
                list2.add(player.getName());

                switch (args[0].toLowerCase()) {
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
                    default -> sendUsage(sender);
                }
            }
            default ->
                    sendUsage(sender);
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/gm <0,1,2,3> [player]\n" +
                "/gm <survival,creative,adventure,spectator> [player]");
    }

    List<String> arguments = new ArrayList<>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        arguments.add("0");
        arguments.add("1");
        arguments.add("2");
        arguments.add("3");
        arguments.add("su");
        arguments.add("c");
        arguments.add("a");
        arguments.add("sp");
        arguments.add("survival");
        arguments.add("creative");
        arguments.add("adventure");
        arguments.add("spectator");

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
            }
            return result;
        }else if (args.length == 2) {
            return null;
        }
        return arguments;
    }
}