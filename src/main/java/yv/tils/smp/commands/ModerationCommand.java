package yv.tils.smp.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.placeholder.ColorCode;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.placeholder.StringReplacer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.7
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
                    String reason1 = new ColorCode().ColorCodes(reasonreplaceplsarg4);

                    if (reason1.length() == 0) {
                        reason1 = LanguageFile.getMessage(LanguageMessage.MOD_NO_REASON);
                    }

                    if (reason.length() == 0) {
                        reason = LanguageFile.getMessage(LanguageMessage.MOD_NO_REASON);
                    }

                    switch (args[0].toLowerCase()) {
                        case "kick":
                            if (sender.hasPermission("yvtils.smp.command.moderation.kick")) {
                                if (onlinetarget != null) {
                                    onlinetarget.kickPlayer(reason);

                                    List<String> list1 = new ArrayList();
                                    List<String> list2 = new ArrayList();
                                    list1.add("PREFIXMODERATION");
                                    list2.add(MessagePlaceholder.PREFIXMODERATION);
                                    list1.add("MODERATOR");
                                    list2.add(sender.getName());
                                    list1.add("PLAYER");
                                    list2.add(target.getName());
                                    list1.add("REASON");
                                    list2.add(reason);

                                    Bukkit.broadcast(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_ANNOUNCEMENT_KICK), list1, list2), "yvtils.smp.command.moderation.kick.getannounced");
                                }else {
                                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_NOT_ONLINE));
                                }
                            } else {
                                player.sendMessage(MessagePlaceholder.PERMISSIONERROR + parentpermission + ".kick");
                            }
                            break;
                        case "ban":
                            if (sender.hasPermission("yvtils.smp.command.moderation.ban")) {
                                if (Bukkit.getBanList(BanList.Type.NAME).isBanned(target.getName())) {
                                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_ALREADY_BANNED));
                                    return false;
                                }
                                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason, null, sender.getName());
                                if (onlinetarget != null) {
                                    onlinetarget.kickPlayer(reason);
                                }

                                List<String> list1 = new ArrayList();
                                List<String> list2 = new ArrayList();
                                list1.add("PREFIXMODERATION");
                                list2.add(MessagePlaceholder.PREFIXMODERATION);
                                list1.add("MODERATOR");
                                list2.add(sender.getName());
                                list1.add("PLAYER");
                                list2.add(target.getName());
                                list1.add("REASON");
                                list2.add(reason);

                                Bukkit.broadcast(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_ANNOUNCEMENT_BAN), list1, list2), "yvtils.smp.command.moderation.ban.getannounced");
                            } else {
                                player.sendMessage(MessagePlaceholder.PERMISSIONERROR + parentpermission + ".ban");
                            }
                            break;
                        case "tempban":

                            Calendar cal = Calendar.getInstance();

                            switch (args[3].toLowerCase()) {
                                case "seconds", "s" -> cal.add(Calendar.SECOND, Integer.parseInt(args[2]));
                                case "minutes", "m" -> cal.add(Calendar.MINUTE, Integer.parseInt((args[2])));
                                case "hours", "h" -> cal.add(Calendar.HOUR, Integer.parseInt(args[2]));
                                case "days", "d" -> cal.add(Calendar.DAY_OF_WEEK, Integer.parseInt(args[2]));
                                default -> sender.sendMessage(LanguageFile.getMessage(LanguageMessage.UNKNOWN_TIME_FORMAT));
                            }

                            if (sender.hasPermission("yvtils.smp.command.moderation.temp.ban")) {
                                if (Bukkit.getBanList(BanList.Type.NAME).isBanned(target.getName())) {
                                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_ALREADY_BANNED));
                                    return false;
                                }
                                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason1, cal.getTime(), sender.getName());
                                if (onlinetarget != null) {
                                    onlinetarget.kickPlayer(reason1);
                                }

                                List<String> list1 = new ArrayList();
                                List<String> list2 = new ArrayList();
                                list1.add("PREFIXMODERATION");
                                list2.add(MessagePlaceholder.PREFIXMODERATION);
                                list1.add("MODERATOR");
                                list2.add(sender.getName());
                                list1.add("PLAYER");
                                list2.add(target.getName());
                                list1.add("REASON");
                                list2.add(reason1);
                                list1.add("DURATION");
                                list2.add(String.valueOf(cal.getTime()));

                                Bukkit.broadcast(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_ANNOUNCEMENT_TEMPBAN), list1, list2), "yvtils.smp.command.moderation.temp.ban.getannounced");
                            } else{
                                player.sendMessage(MessagePlaceholder.PERMISSIONERROR + parentpermission + ".temp.ban");
                            }
                            break;
                        case "unban":
                            if (sender.hasPermission("yvtils.smp.command.moderation.unban")) {
                                if (Bukkit.getBanList(BanList.Type.NAME).isBanned(target.getName())) {
                                    Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());

                                    List<String> list1 = new ArrayList();
                                    List<String> list2 = new ArrayList();
                                    list1.add("PREFIXMODERATION");
                                    list2.add(MessagePlaceholder.PREFIXMODERATION);
                                    list1.add("MODERATOR");
                                    list2.add(sender.getName());
                                    list1.add("PLAYER");
                                    list2.add(target.getName());

                                    Bukkit.broadcast(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_ANNOUNCEMENT_UNBAN), list1, list2), "yvtils.smp.command.moderation.unban.getannounced");
                                }else {

                                    List<String> list1 = new ArrayList();
                                    List<String> list2 = new ArrayList();
                                    list1.add("PREFIXMODERATION");
                                    list2.add(MessagePlaceholder.PREFIXMODERATION);
                                    list1.add("PLAYER");
                                    list2.add(target.getName());

                                    sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_PLAYER_NOT_BANNED), list1, list2));
                                }} else {
                                    player.sendMessage(MessagePlaceholder.PERMISSIONERROR + parentpermission + ".unban");
                            }
                            break;
                        default:
                            sendUsage(sender);
                    }} else {
                        player.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_UNKNOWN));
                }} else {
                sendUsage(sender);
            }}
        return true;
    }
                private void sendUsage(CommandSender sender){
                    sender.sendMessage( LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE)+ " " + ChatColor.BLUE +
                    "/moderation <kick, ban> <Player Name> [Reason (\\n for new Line, Color Codes are working when using '&')] \n" +
                    "/moderation tempban <Player Name> <duration> <Time Format (Seconds, Minutes, Hours, Days)> [Reason (\\n for new Line, Color Codes are working when using '&')] \n" +
                            "/moderation unban <Player Name>");
    }}