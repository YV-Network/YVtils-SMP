package yv.tils.smp.manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
public class Speed implements CommandExecutor, TabCompleter {

    List<Float> speedList = List.of(
            -1.0f, -0.9f, -0.8f, -0.7f, -0.6f,
            -0.5f, -0.4f, -0.3f, -0.2f, -0.1f,
            0.0f, 0.1f, 0.2f, 0.3f, 0.4f,
            0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f
    );

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sendUsage(sender);
            return false;
        }


        List<String> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        list1.add("SPEED");
        list2.add(args[0]);

        String speedChangeSelf = new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.SPEED_CHANGE_SELF), list1, list2);
        String speedChangeOther = null;

        if (args.length == 2) {
            list1.add("PLAYER");
            list2.add(args[1]);

            speedChangeOther = new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.SPEED_CHANGE_OTHER), list1, list2);
        }

        float speed = 0;

        if (args[0].equalsIgnoreCase("reset")) {
            resetSpeed(sender, args);
            return false;
        } else {
            try {
                speed = Float.parseFloat(args[0]);

                if (speed > 10.0f || speed < -10.0f) {
                    sendUsage(sender);
                    return false;
                } else if (!speedList.contains(speed)) {
                    speed = speed / 10;
                }
            } catch (NumberFormatException ignored) {
                sendUsage(sender);
                return false;
            }
        }

        changeSpeed(sender, speed, args, speedChangeSelf, speedChangeOther);

        return false;
    }

    private void changeSpeed(CommandSender sender, float speed, String[] args, String speedChangeSelf, String speedChangeOther) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                Player player = (Player) sender;
                changeSpeed(player, speed);
                player.sendMessage(speedChangeSelf);
            } else if (args.length == 2) {
                Player player = Bukkit.getPlayer(args[1]);
                changeSpeed(player, speed);
                sender.sendMessage(speedChangeOther);
                player.sendMessage(speedChangeSelf);
            } else {
                sendUsage(sender);
            }
        } else if (args.length != 2) {
            sender.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_ARGUMENT_MISSING));
            sendUsage(sender);
        } else {
            Player player = Bukkit.getPlayer(args[1]);
            changeSpeed(player, speed);
            sender.sendMessage(speedChangeOther);
            player.sendMessage(speedChangeSelf);
        }
    }

    private void resetSpeed(CommandSender sender, String[] args) {

        String speedResetSelf = LanguageFile.getMessage(LanguageMessage.SPEED_RESET_SELF);
        String speedResetOther = null;

        if (args.length == 2) {
            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("PLAYER");
            list2.add(args[1]);

            speedResetOther = new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.SPEED_RESET_OTHER), list1, list2);
        }

        if (sender instanceof Player) {
            if (args.length == 1) {
                Player player = (Player) sender;
                changeSpeed(player, 0.2f);
                player.sendMessage(speedResetSelf);
            } else if (args.length == 2) {
                Player player = Bukkit.getPlayer(args[1]);
                changeSpeed(player, 0.2f);
                sender.sendMessage(speedResetOther);
                player.sendMessage(speedResetSelf);
            } else {
                sendUsage(sender);
            }
        } else if (args.length != 2) {
            sender.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_ARGUMENT_MISSING));
            sendUsage(sender);
        } else {
            Player player = Bukkit.getPlayer(args[1]);
            changeSpeed(player, 0.2f);
            sender.sendMessage(speedResetOther);
            player.sendMessage(speedResetSelf);
        }
    }

    private void changeSpeed(Player player, float speed) {
        player.setWalkSpeed(speed);
        player.setFlySpeed(speed);
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/speed <-10 - 10> or <-1.0, -0.9 - 0.9, 1.0> [player]\n" +
                "/speed reset [player]");
    }


    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            result.add("reset");
            result.add("<-10 - 10>");
            return result;
        } else {
            return null;
        }
    }
}