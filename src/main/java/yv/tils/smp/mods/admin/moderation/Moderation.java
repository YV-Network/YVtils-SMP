package yv.tils.smp.mods.admin.moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import yv.tils.smp.placeholder.Prefix;
import yv.tils.smp.utils.MojangAPI;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.internalapi.StringReplacer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class Moderation implements CommandExecutor, TabCompleter {

    private final String parentPermission = "yvtils.smp.command.moderation";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        String subCMD = args[0];

        Player target = Bukkit.getServer().getPlayer(args[1]);
        OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(MojangAPI.Name2UUID(args[1]));

        switch (subCMD) {

            case "kick" -> {
                if (args.length < 2) {
                    sendUsage(sender);
                    return false;
                }

                String reason;

                if (args.length == 2) {
                    reason = LanguageFile.getMessage(LanguageMessage.MOD_NO_REASON);
                } else {
                    reason = getReason(args, 2);
                }

                playerKick(sender, target, reason);
                return true;
            }

            case "ban" -> {
                if (args.length < 2) {
                    sendUsage(sender);
                    return false;
                }

                String reason;

                if (args.length == 2) {
                    reason = LanguageFile.getMessage(LanguageMessage.MOD_NO_REASON);
                } else {
                    reason = getReason(args, 2);
                }

                if (target == null) {
                    playerBan(sender, offlineTarget, reason);
                    return true;
                }

                playerBan(sender, target, reason);
                return true;
            }

            case "tempban" -> {
                if (args.length < 5) {
                    sendUsage(sender);
                    return false;
                }

                String reason;

                if (args.length == 5) {
                    reason = LanguageFile.getMessage(LanguageMessage.MOD_NO_REASON);
                } else {
                    reason = getReason(args, 5);
                }

                Calendar cal = Calendar.getInstance();
                switch (args[3].toLowerCase()) {
                    case "seconds", "s" -> cal.add(Calendar.SECOND, Integer.parseInt(args[2]));
                    case "minutes", "m" -> cal.add(Calendar.MINUTE, Integer.parseInt((args[2])));
                    case "hours", "h" -> cal.add(Calendar.HOUR, Integer.parseInt(args[2]));
                    case "days", "d" -> cal.add(Calendar.DAY_OF_WEEK, Integer.parseInt(args[2]));
                    default -> {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.UNKNOWN_TIME_FORMAT));
                        return false;
                    }
                }

                if (target == null) {
                    playerTempBan(sender, offlineTarget, reason, cal);
                    return true;
                }

                playerTempBan(sender, target, reason, cal);
                return true;
            }

            case "unban" -> {
                if (args.length < 2) {
                    sendUsage(sender);
                    return false;
                }

                if (target == null) {
                    playerUnban(sender, offlineTarget);
                    return true;
                }

                playerUnban(sender, target);
                return true;
            }

            default -> {
                sendUsage(sender);
                return false;
            }
        }
    }

    public void playerKick(CommandSender mod, Player target, String reason) {
        if (mod.hasPermission("yvtils.smp.command.moderation.kick")) {
            if (target != null) {
                target.kickPlayer(reason);

                List<String> list1 = new ArrayList<>();
                List<String> list2 = new ArrayList<>();
                list1.add("PREFIXMODERATION");
                list2.add(Prefix.PREFIXMODERATION);
                list1.add("MODERATOR");
                list2.add(mod.getName());
                list1.add("PLAYER");
                list2.add(target.getName());
                list1.add("REASON");
                list2.add(reason);

                Bukkit.broadcast(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_ANNOUNCEMENT_KICK), list1, list2),
                        "yvtils.smp.command.moderation.announcement");
            } else {
                mod.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_NOT_ONLINE));
            }
        } else {
            mod.sendMessage(Prefix.PERMISSIONERROR + parentPermission + ".kick");
        }
    }

    public void playerBan(CommandSender mod, Player target, String reason) {
        if (mod.hasPermission("yvtils.smp.command.moderation.ban")) {
            if (target.isBanned()) {
                mod.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_ALREADY_BANNED));
                return;
            }
            target.ban(reason, (Date) null, mod.getName(), true);

            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            list1.add("PREFIXMODERATION");
            list2.add(Prefix.PREFIXMODERATION);
            list1.add("MODERATOR");
            list2.add(mod.getName());
            list1.add("PLAYER");
            list2.add(target.getName());
            list1.add("REASON");
            list2.add(reason);

            Bukkit.broadcast(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_ANNOUNCEMENT_BAN), list1, list2),
                    "yvtils.smp.command.moderation.announcement");
        } else {
            mod.sendMessage(Prefix.PERMISSIONERROR + parentPermission + ".ban");
        }
    }

    public void playerBan(CommandSender mod, OfflinePlayer target, String reason) {
        if (mod.hasPermission("yvtils.smp.command.moderation.ban")) {
            if (target.isBanned()) {
                mod.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_ALREADY_BANNED));
                return;
            }
            target.ban(reason, (Date) null, mod.getName());

            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            list1.add("PREFIXMODERATION");
            list2.add(Prefix.PREFIXMODERATION);
            list1.add("MODERATOR");
            list2.add(mod.getName());
            list1.add("PLAYER");
            list2.add(MojangAPI.UUID2Name(target.getUniqueId()));
            list1.add("REASON");
            list2.add(reason);

            Bukkit.broadcast(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_ANNOUNCEMENT_BAN), list1, list2),
                    "yvtils.smp.command.moderation.announcement");
        } else {
            mod.sendMessage(Prefix.PERMISSIONERROR + parentPermission + ".ban");
        }
    }

    public void playerTempBan(CommandSender mod, Player target, String reason, Calendar duration) {
        if (mod.hasPermission("yvtils.smp.command.moderation.tempban")) {
            if (target.isBanned()) {
                mod.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_ALREADY_BANNED));
                return;
            }

            target.ban(reason, duration.getTime(), mod.getName(), true);

            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            list1.add("PREFIXMODERATION");
            list2.add(Prefix.PREFIXMODERATION);
            list1.add("MODERATOR");
            list2.add(mod.getName());
            list1.add("PLAYER");
            list2.add(target.getName());
            list1.add("REASON");
            list2.add(reason);
            list1.add("DURATION");
            list2.add(String.valueOf(duration.getTime()));

            Bukkit.broadcast(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_ANNOUNCEMENT_TEMPBAN), list1, list2),
                    "yvtils.smp.command.moderation.announcement");
        } else {
            mod.sendMessage(Prefix.PERMISSIONERROR + parentPermission + ".tempban");
        }
    }

    public void playerTempBan(CommandSender mod, OfflinePlayer target, String reason, Calendar duration) {
        if (mod.hasPermission("yvtils.smp.command.moderation.tempban")) {
            if (target.isBanned()) {
                mod.sendMessage(LanguageFile.getMessage(LanguageMessage.PLAYER_ALREADY_BANNED));
                return;
            }

            target.ban(reason, duration.getTime(), mod.getName());

            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            list1.add("PREFIXMODERATION");
            list2.add(Prefix.PREFIXMODERATION);
            list1.add("MODERATOR");
            list2.add(mod.getName());
            list1.add("PLAYER");
            list2.add(MojangAPI.UUID2Name(target.getUniqueId()));
            list1.add("REASON");
            list2.add(reason);
            list1.add("DURATION");
            list2.add(String.valueOf(duration.getTime()));

            Bukkit.broadcast(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_ANNOUNCEMENT_TEMPBAN), list1, list2),
                    "yvtils.smp.command.moderation.announcement");
        } else {
            mod.sendMessage(Prefix.PERMISSIONERROR + parentPermission + ".tempban");
        }
    }

    public void playerUnban(CommandSender mod, OfflinePlayer target) {
        if (mod.hasPermission("yvtils.smp.command.moderation.unban")) {
            if (target.isBanned()) {
                Date date = new Date();
                date.setTime(0L);

                target.ban("", date, "");

                List<String> list1 = new ArrayList<>();
                List<String> list2 = new ArrayList<>();
                list1.add("PREFIXMODERATION");
                list2.add(Prefix.PREFIXMODERATION);
                list1.add("MODERATOR");
                list2.add(mod.getName());
                list1.add("PLAYER");
                list2.add(MojangAPI.UUID2Name(target.getUniqueId()));

                Bukkit.broadcast(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_ANNOUNCEMENT_UNBAN), list1, list2),
                        "yvtils.smp.command.moderation.announcement");
            } else {
                List<String> list1 = new ArrayList<>();
                List<String> list2 = new ArrayList<>();
                list1.add("PREFIXMODERATION");
                list2.add(Prefix.PREFIXMODERATION);
                list1.add("PLAYER");
                list2.add(MojangAPI.UUID2Name(target.getUniqueId()));

                mod.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MOD_PLAYER_NOT_BANNED), list1, list2));
            }
        } else {
            mod.sendMessage(Prefix.PERMISSIONERROR + parentPermission + ".unban");
        }
    }

    public void playerMute(CommandSender mod, Player target, String reason) {

    }

    public void playerTempMute(CommandSender mod, Player target, String reason, Calendar duration) {

    }

    public void playerUnmute(CommandSender mod, Player target) {

    }

    public String getReason(String[] args, int start) {
        StringBuilder reason = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            reason.append(args[i]).append(" ");
        }
        return reason.toString();
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/moderation <kick, ban> <Player Name> [Reason] \n" +
                "/moderation tempban <Player Name> <duration> <Time Format (Seconds, Minutes, Hours, Days)> [Reason] \n" +
                "/moderation unban <Player Name>");
    }

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            results.add("kick");
            results.add("tempban");
            results.add("ban");
            results.add("unban");
        } else if (args.length == 2) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                results.add(player.getName());
            }
        } else if (args.length == 3) {
            if (args[0].equals("tempban")) {
                results.add(LanguageFile.getMessage(LanguageMessage.TAB_COMPLETER_MOD_COMMAND_DURATION));
            } else {
                results.add(LanguageFile.getMessage(LanguageMessage.TAB_COMPLETER_MOD_COMMAND_REASON));
            }
        } else if (args.length == 4) {
            if (args[0].equals("tempban")) {
                results.add("Seconds");
                results.add("Minutes");
                results.add("Hours");
                results.add("Days");
                results.add("s");
                results.add("m");
                results.add("h");
                results.add("d");
            }
        } else if (args.length == 5) {
            if (args[0].equals("tempban")) {
                results.add(LanguageFile.getMessage(LanguageMessage.TAB_COMPLETER_MOD_COMMAND_REASON));
            }
        }
        return results;
    }
}