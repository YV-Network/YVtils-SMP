package yv.tils.smp.mods.admin.vanish;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.mods.other.SpawnElytra;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class Vanish implements CommandExecutor {

    private static Vanish instance;
    public static Vanish getInstance() {return instance;}

    public static Map<UUID, Integer> vanish = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        instance = this;

        Player player = (Player) sender;

        if (!vanish.containsKey(player.getUniqueId())) {
            vanish.put(player.getUniqueId(), 1);
        }else {
            vanish.remove(player.getUniqueId());
        }

        return false;
    }
}