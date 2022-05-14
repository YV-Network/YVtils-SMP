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
public class MainteanceAutoCompleter implements TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            results.add("on");
            results.add("off");
            results.add("true");
            results.add("false");
            results.add("toggle");
            results.add("status");
        }
        return results;
    }
}

