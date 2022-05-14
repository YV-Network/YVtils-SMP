package yv.tils.smp.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.placeholder.ColorCode;
import yv.tils.smp.LanguageSystem.LanguagePlaceholder;
import yv.tils.smp.placeholder.MessagePlaceholder;

import java.util.Calendar;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
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
                    StringBuilder builder1 = new StringBuilder();
                    for (int x = 2; x < args.length; x++) {
                        builder.append(args[x]).append(" ");
                    }
                    for (int x = 4; x < args.length; x++) {
                        builder1.append(args[x]).append(" ");
                    }
                    String reasonreplaceplsarg2 = builder.toString();
                    String reasonreplaceplsarg4 = builder1.toString();

                    String reason = new ColorCode().ColorCodes(reasonreplaceplsarg2);
                    String reason1 = new ColorCode().ColorCodes(reasonreplaceplsarg2);




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
                    "/moderation <kick, ban> <Player Name> [Reason (\\n for new Line, Color Codes are working when using '&')] \n" +
                    "/moderation tempban <Player Name> <duration> <Time Format (Seconds, Minutes, Hours, Days)> [Reason (\\n for new Line, Color Codes are working when using '&')] \n" +
                            "/moderation unban <Player Name>");
    }
}
