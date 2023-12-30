package yv.tils.smp.mods.status;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import yv.tils.smp.utils.configs.status.StatusConfigManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @version CH2-1.0.0
 * @since 4.6.6
 */
public class StatusCommandCompleter implements TabCompleter {
    List<String> defaults = new StatusConfigManager().ConfigRequest().getStringList("Default-Status");

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            results.add("clear");
            results.add("set");
            results.add("default");
        }
        if (Objects.equals(args[0], "set")) {
            results.add("[Prefix]");
        }
        if (Objects.equals(args[0], "default")) {
            results.addAll(defaults);
        }
        if (Objects.equals(args[0], "clear")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                results.add(player.getName());
            }
        }
        return results;
    }
}
