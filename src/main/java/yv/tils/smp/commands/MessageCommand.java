package yv.tils.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.LanguageSystem.LanguagePlaceholder;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.MessagePlaceholder;

import java.util.Collections;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class MessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder builder = new StringBuilder();
                    for (int x = 1; x < args.length; x++) {
                        builder.append(args[x]).append(" ");
                    }
                    String colorcodereplace = builder.toString();

                    String colorcode = colorcodereplace.replace("&0", "§0");
                    String colorcode1 = colorcode.replace("&1", "§1");
                    String colorcode2 = colorcode1.replace("&2", "§2");
                    String colorcode3 = colorcode2.replace("&3", "§3");
                    String colorcode4 = colorcode3.replace("&4", "§4");
                    String colorcode5 = colorcode4.replace("&5", "§5");
                    String colorcode6 = colorcode5.replace("&6", "§6");
                    String colorcode7 = colorcode6.replace("&7", "§7");
                    String colorcode8 = colorcode7.replace("&8", "§8");
                    String colorcode9 = colorcode8.replace("&9", "§9");
                    String colorcode10 = colorcode9.replace("&a", "§a");
                    String colorcode11 = colorcode10.replace("&b", "§b");
                    String colorcode12 = colorcode11.replace("&c", "§c");
                    String colorcode13 = colorcode12.replace("&d", "§d");
                    String colorcode14 = colorcode13.replace("&e", "§e");
                    String colorcode15 = colorcode14.replace("&f", "§f");
                    String colorcode16 = colorcode15.replace("&k", "§k");
                    String colorcode17 = colorcode16.replace("&l", "§l");
                    String colorcode18 = colorcode17.replace("&m", "§m");
                    String colorcode19 = colorcode18.replace("&n", "§n");
                    String colorcode20 = colorcode19.replace("&o", "§o");
                    String colorcode21 = colorcode20.replace("&r", "§r");
                    colorcodereplace = colorcode21;

                    List<String> list2 = SMPPlugin.getInstance().getConfig().getStringList("DirectMessage.Design");

                    for (int i = 0; i < list2.size(); i++) {
                        list2.set(i, list2.get(i).replace("receiver", target.getName()));
                        list2.set(i, list2.get(i).replace("sender", player.getName()));
                    }

                    Collections.shuffle(list2);
                    String senderreciverdesign = list2.get(0);



                    if (player.getUniqueId() == target.getUniqueId()) {
                        player.sendMessage("§e[§cNote§e] §f" + colorcodereplace);
                    }else {
                        player.sendMessage(senderreciverdesign + " " + colorcodereplace);
                        target.sendMessage(senderreciverdesign + " " + colorcodereplace);

                        SMPPlugin.getInstance().getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                    }
                }else {
                    player.sendMessage(MessagePlaceholder.PREFIX + " §4Unknown Player");
                }
            }else {
                sendUsage(sender);
            }}
        return false;
    }

    private void sendUsage(CommandSender sender){
        sender.sendMessage(LanguagePlaceholder.CommandUsage() + ChatColor.BLUE +
                "/dm <player> <message> <- Color Codes accepted! Use them with '&'!");
    }}