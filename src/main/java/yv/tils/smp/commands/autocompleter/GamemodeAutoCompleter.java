package yv.tils.smp.commands.autocompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.7
 * @deprecated
 */
public class GamemodeAutoCompleter implements TabCompleter {
    List<String> arguments = new ArrayList<>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("0");
            arguments.add("1");
            arguments.add("2");
            arguments.add("3");
            arguments.add("su");
            arguments.add("c");
            arguments.add("a");
            arguments.add("sp");
        }

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }

            }
            return result;
        }else if (args.length == 2) {
            return null;
        }
        return arguments;
    }
}
