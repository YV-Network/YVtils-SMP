package yv.tils.smp.mods.status;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import yv.tils.smp.utils.configs.ConfigModeration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class StatusCommandCompleter implements TabCompleter {
    List<String> defaultstatus = new ConfigModeration().ConfigRequest("StatusModule").getStringList("Default-Status");
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
            results.addAll(defaultstatus);
        }
        if (Objects.equals(args[0], "clear")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                results.add(player.getName());
            }
        }
        return results;
    }
}
