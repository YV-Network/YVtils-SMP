package yv.tils.smp.commands.autocompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class VanishAutoCompleter implements TabCompleter{
    List<String> arguments = new ArrayList<>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

        if (arguments.isEmpty()) {
            arguments.add("itempickup");
            arguments.add("on");
            arguments.add("off");
            arguments.add("help");
        }

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }}
            return result;
        }
        return arguments;
    }}