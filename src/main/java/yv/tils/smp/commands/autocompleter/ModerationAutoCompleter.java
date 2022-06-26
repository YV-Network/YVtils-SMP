package yv.tils.smp.commands.autocompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import yv.tils.smp.LanguageSystem.LanguageFile;
import yv.tils.smp.LanguageSystem.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class ModerationAutoCompleter implements TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            results.add("kick");
            results.add("tempban");
            results.add("ban");
            results.add("unban");
        }else if (args.length == 2) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    results.add(player.getName());
            }
        }else if (args.length == 3) {
            if (args[0].equals("tempban")) {
                results.add(LanguageFile.getMessage(LanguageMessage.TAB_COMPLETER_MOD_COMMAND_DURATION));
            }else {
                results.add(LanguageFile.getMessage(LanguageMessage.TAB_COMPLETER_MOD_COMMAND_REASON));
            }
        }else if (args.length == 4) {
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
        }else if (args.length == 5) {
            if (args[0].equals("tempban")) {
                results.add(LanguageFile.getMessage(LanguageMessage.TAB_COMPLETER_MOD_COMMAND_REASON));
            }
        }
        return results;
    }
}
