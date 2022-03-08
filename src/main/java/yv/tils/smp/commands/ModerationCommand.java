package yv.tils.smp.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.placeholder.LanguagePlaceholder;
import yv.tils.smp.placeholder.MessagePlaceholder;

import java.util.Calendar;

public class ModerationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {

            Player player = (Player) sender;
            String parentpermission = "yvtils.smp.command.moderation";


            if (args.length >= 2) {
                if (Bukkit.getOfflinePlayer(args[1]) != null) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[1].toLowerCase());
                    Player onlinetarget = Bukkit.getPlayer(args[1].toLowerCase());

                    StringBuilder builder = new StringBuilder();
                    for (int x = 2; x < args.length; x++) {
                        builder.append(args[x]).append(" ");
                    }
                    String reason = builder.toString();

                    String colorcode = reason.replace("&0", "§0");
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
                    reason = colorcode21;


                    StringBuilder builder1 = new StringBuilder();
                    for (int x = 4; x < args.length; x++) {
                        builder1.append(args[x]).append(" ");
                    }
                    String reason1 = builder1.toString();


                    String Colorcode = reason1.replace("&0", "§0");
                    String Colorcode1 = Colorcode.replace("&1", "§1");
                    String Colorcode2 = Colorcode1.replace("&2", "§2");
                    String Colorcode3 = Colorcode2.replace("&3", "§3");
                    String Colorcode4 = Colorcode3.replace("&4", "§4");
                    String Colorcode5 = Colorcode4.replace("&5", "§5");
                    String Colorcode6 = Colorcode5.replace("&6", "§6");
                    String Colorcode7 = Colorcode6.replace("&7", "§7");
                    String Colorcode8 = Colorcode7.replace("&8", "§8");
                    String Colorcode9 = Colorcode8.replace("&9", "§9");
                    String Colorcode10 = Colorcode9.replace("&a", "§a");
                    String Colorcode11 = Colorcode10.replace("&b", "§b");
                    String Colorcode12 = Colorcode11.replace("&c", "§c");
                    String Colorcode13 = Colorcode12.replace("&d", "§d");
                    String Colorcode14 = Colorcode13.replace("&e", "§e");
                    String Colorcode15 = Colorcode14.replace("&f", "§f");
                    String Colorcode16 = Colorcode15.replace("&k", "§k");
                    String Colorcode17 = Colorcode16.replace("&l", "§l");
                    String Colorcode18 = Colorcode17.replace("&m", "§m");
                    String Colorcode19 = Colorcode18.replace("&n", "§n");
                    String Colorcode20 = Colorcode19.replace("&o", "§o");
                    String Colorcode21 = Colorcode20.replace("&r", "§r");
                    reason1 = Colorcode21;

                    if (reason1.length() == 0) {
                        reason1 = "No Reason was given";
                    }

                    if (reason.length() == 0) {
                        reason = "No Reason was given";
                    }


                    switch (args[0].toLowerCase()) {
                        case "kick":
                            if (sender.hasPermission("yvtils.smp.command.moderation.kick")) {
                                if (onlinetarget != null) {
                                    onlinetarget.kickPlayer(reason);
                                    Bukkit.broadcast(MessagePlaceholder.PREFIXMODERATION + " §7" + target.getName() + " got kicked from " + sender.getName() + "! Reason: " + reason, "yvtils.smp.command.moderation.kick.getannounced");
                                }else {
                                    sender.sendMessage(LanguagePlaceholder.PlayerNotOnline());
                                }
                            } else {
                                player.sendMessage(MessagePlaceholder.PERMISSIONERROR + parentpermission + ".kick");
                            }
                            break;
                        case "ban":
                            if (sender.hasPermission("yvtils.smp.command.moderation.ban")) {
                                if (Bukkit.getBanList(BanList.Type.NAME).isBanned(target.getName())) {
                                    sender.sendMessage(LanguagePlaceholder.AlreadyBanned());
                                    return false;
                                }
                                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason, null, String.valueOf(sender));
                                if (onlinetarget != null) {
                                    onlinetarget.kickPlayer(reason);
                                }
                                Bukkit.broadcast(MessagePlaceholder.PREFIXMODERATION + " §7" + target.getName() + " got banned from " + sender.getName() + "! Reason: " + reason, "yvtils.smp.command.moderation.ban.getannounced");
                            } else {
                                player.sendMessage(MessagePlaceholder.PERMISSIONERROR + parentpermission + ".ban");
                            }
                            break;
                        case "tempban":

                            Calendar cal = Calendar.getInstance();
                            int intValue;

                            switch (args[3].toLowerCase()) {
                                case "seconds" -> cal.add(Calendar.SECOND, Integer.parseInt(args[2]));
                                case "minutes" -> cal.add(Calendar.MINUTE, Integer.parseInt((args[2])));
                                case "hours" -> cal.add(Calendar.HOUR, Integer.parseInt(args[2]));
                                case "days" -> cal.add(Calendar.DAY_OF_WEEK, Integer.parseInt(args[2]));
                                default -> sender.sendMessage(LanguagePlaceholder.UnknownTimeFormat());
                            }

                            if (sender.hasPermission("yvtils.smp.command.moderation.temp.ban")) {
                                if (Bukkit.getBanList(BanList.Type.NAME).isBanned(target.getName())) {
                                    sender.sendMessage(LanguagePlaceholder.AlreadyBanned());
                                    return false;
                                }
                                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason1, cal.getTime(), String.valueOf(sender));
                                        if (onlinetarget != null) {
                                            onlinetarget.kickPlayer(reason);
                                        }
                                        Bukkit.broadcast(MessagePlaceholder.PREFIXMODERATION + " §8" + target.getName() + " §7got temp banned from §8" + sender.getName() + "§7! Reason: §8" + reason1 + "§7, Duration: §8" + args[2] + " " + args[3], "yvtils.smp.command.moderation.temp.ban.getannounced");
                            } else{
                                player.sendMessage(MessagePlaceholder.PERMISSIONERROR + parentpermission + ".temp.ban");
                            }
                            break;
                        case "unban":
                            if (sender.hasPermission("yvtils.smp.command.moderation.unban")) {
                                if (Bukkit.getBanList(BanList.Type.NAME).isBanned(target.getName())) {
                                    Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());

                                    Bukkit.broadcast(MessagePlaceholder.PREFIXMODERATION + " §7" + target.getName() + " got unbanned from " + sender.getName() + "!", "yvtils.smp.command.moderation.unban.getannounced");
                                }else {
                                    sender.sendMessage(MessagePlaceholder.PREFIXMODERATION + " " + target.getName() + " is not banned!");
                                }
                            } else {
                                player.sendMessage(MessagePlaceholder.PERMISSIONERROR + parentpermission + ".unban");
                            }
                            break;
                        default:
                            sendUsage(sender);
                    }
                } else {
                    player.sendMessage(MessagePlaceholder.PREFIXMODERATION + " §4Unknown Player");
                }
            } else {
                sendUsage(sender);
            }
        }
        return true;
    }
                private void sendUsage(CommandSender sender){
                    sender.sendMessage(LanguagePlaceholder.CommandUsage() + ChatColor.BLUE +
                    "/modearation <kick, ban> <Player Name> [Reason (\\n for new Line, Color Codes are working when using '&')] \n" +
                    "/modearation tempban <Player Name> <duration> <Time Format (Seconds, Minutes, Hours, Days)> [Reason (\\n for new Line, Color Codes are working when using '&')] \n" +
                            "/modearation unban <Player Name>");
    }
}
