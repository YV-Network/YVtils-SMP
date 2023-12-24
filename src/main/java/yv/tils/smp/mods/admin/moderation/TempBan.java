package yv.tils.smp.mods.admin.moderation;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import yv.tils.smp.utils.MojangAPI;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class TempBan implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length < 3) {
            sendUsage(sender);
            return false;
        }

        String reason;

        if (args.length == 4) {
            reason = LanguageFile.getMessage(LanguageMessage.MOD_NO_REASON);
        }else {
            reason = new Moderation().getReason(args, 3);
        }

        Player target = Bukkit.getServer().getPlayer(args[0]);
        OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(MojangAPI.Name2UUID(args[0]));

        Calendar cal = Calendar.getInstance();
        switch (args[2].toLowerCase()) {
            case "seconds", "s" -> cal.add(Calendar.SECOND, Integer.parseInt(args[1]));
            case "minutes", "m" -> cal.add(Calendar.MINUTE, Integer.parseInt((args[1])));
            case "hours", "h" -> cal.add(Calendar.HOUR, Integer.parseInt(args[1]));
            case "days", "d" -> cal.add(Calendar.DAY_OF_WEEK, Integer.parseInt(args[1]));
            default -> {
                sender.sendMessage(LanguageFile.getMessage(LanguageMessage.UNKNOWN_TIME_FORMAT));
                return false;
            }
        }

        if (target == null) {
            new Moderation().playerTempBan(sender, offlineTarget, reason, cal);
            return true;
        }

        new Moderation().playerTempBan(sender, target, reason, cal);

        return true;
    }

    private void sendUsage(@NotNull CommandSender sender) {
        sender.sendMessage("§cUsage: /tempban <Player Name> <duration> <Time Format (Seconds, Minutes, Hours, Days)> [Reason]");
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                results.add(player.getName());
            }
        }else if (args.length == 2) {
            results.add(LanguageFile.getMessage(LanguageMessage.TAB_COMPLETER_MOD_COMMAND_DURATION));
        }else if (args.length == 3) {
            results.add("Seconds");
            results.add("Minutes");
            results.add("Hours");
            results.add("Days");
            results.add("s");
            results.add("m");
            results.add("h");
            results.add("d");
        }else if (args.length == 4) {
            results.add(LanguageFile.getMessage(LanguageMessage.TAB_COMPLETER_MOD_COMMAND_REASON));
        }
        return results;
    }
}