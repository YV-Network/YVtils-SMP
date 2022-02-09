package yv.tils.smp.commands.autocompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class FlySpeedAutoCompleter implements TabCompleter {

    List<String> arguments = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("reset");
            arguments.add("<-10 - 10>");
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
