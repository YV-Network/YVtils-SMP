package yv.tils.smp.commands.autocompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ModerationAutoCompleter implements TabCompleter {
    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("kick <player name> [Reason]");
            arguments.add("ban <player name> [Reason]");
            arguments.add("tempban <player name> <duration> <Seconds, Minutes, Hours, Days> [Reason]");
            arguments.add("unban <player name>");
        }

        List<String> result = new ArrayList<String>();
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
