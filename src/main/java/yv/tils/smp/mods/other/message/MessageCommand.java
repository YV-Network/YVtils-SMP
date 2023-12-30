package yv.tils.smp.mods.other.message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import yv.tils.smp.YVtils;
import yv.tils.smp.utils.color.HexSupport;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.internalapi.StringReplacer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class MessageCommand implements CommandExecutor, TabCompleter {

    List<String> arguments = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder builder = new StringBuilder();

                    for (int x = 1; x < args.length; x++) {
                        builder.append(args[x]).append(" ");
                    }

                    String coloredMessage = HexSupport.hex(builder.toString());

                    List<String> list2 = YVtils.getInstance().getConfig().getStringList("DirectMessage.Design");
                    Collections.shuffle(list2);

                    for (int i = 0; i < list2.size(); i++) {
                        list2.set(i, list2.get(i).replace("receiver", target.getName()));
                        list2.set(i, list2.get(i).replace("sender", player.getName()));
                    }

                    String senderReceiverDesign = list2.get(0);

                    if (player.getUniqueId() == target.getUniqueId()) {
                        List<String> list4 = new ArrayList<>();
                        List<String> list3 = new ArrayList<>();
                        list3.add("MESSAGE");
                        list4.add(coloredMessage);

                        player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MSG_NOTE), list3, list4));
                    } else {
                        player.sendMessage(senderReceiverDesign + " " + coloredMessage);
                        target.sendMessage(senderReceiverDesign + " " + coloredMessage);

                        YVtils.getInstance().getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                        YVtils.getInstance().getRecentMessages().put(target.getUniqueId(), player.getUniqueId());
                    }
                } else if (args[0].equalsIgnoreCase("console")) {
                    StringBuilder builder = new StringBuilder();

                    for (int x = 1; x < args.length; x++) {
                        builder.append(args[x]).append(" ");
                    }

                    String coloredMessage = HexSupport.hex(builder.toString());

                    List<String> list2 = YVtils.getInstance().getConfig().getStringList("DirectMessage.Design");
                    Collections.shuffle(list2);

                    for (int i = 0; i < list2.size(); i++) {
                        list2.set(i, list2.get(i).replace("receiver", "Console"));
                        list2.set(i, list2.get(i).replace("sender", player.getName()));
                    }

                    String senderReceiverDesign = list2.get(0);

                    player.sendMessage(senderReceiverDesign + " " + coloredMessage);
                    Bukkit.getConsoleSender().sendMessage(senderReceiverDesign + " " + coloredMessage);
                } else {
                    player.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_UNKNOWN));
                }
            } else {
                sendUsage(sender);
            }
        } else {
            if (Bukkit.getPlayerExact(args[0]) != null) {
                Player target = Bukkit.getPlayerExact(args[0]);

                StringBuilder builder = new StringBuilder();

                for (int x = 1; x < args.length; x++) {
                    builder.append(args[x]).append(" ");
                }

                String coloredMessage = HexSupport.hex(builder.toString());

                List<String> list2 = YVtils.getInstance().getConfig().getStringList("DirectMessage.Design");
                Collections.shuffle(list2);

                for (int i = 0; i < list2.size(); i++) {
                    list2.set(i, list2.get(i).replace("receiver", target.getName()));
                    list2.set(i, list2.get(i).replace("sender", "Console"));
                }

                String senderReceiverDesign = list2.get(0);


                Bukkit.getConsoleSender().sendMessage(senderReceiverDesign + " " + coloredMessage);
                target.sendMessage(senderReceiverDesign + " " + coloredMessage);
            }
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/msg <player> <message>");
    }

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String[] args) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            arguments.add(player.getName());
        }
        arguments.add("Console");

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
            }
            return result;
        }
        return arguments;
    }

}
