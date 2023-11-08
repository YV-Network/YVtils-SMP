package yv.tils.smp.mods.server.connect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import yv.tils.smp.YVtils;
import yv.tils.smp.placeholder.MessagePlaceholder;

import java.util.Collections;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class PlayerQuit {
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();
        List<String> list = YVtils.getInstance().getConfig().getStringList("QuitMessage");

        list.replaceAll(s -> s.replace("player", playerName));

        Collections.shuffle(list);
        String quitMessage = list.get(0);

        if (YVtils.getInstance().getConfig().getBoolean("SendQuitMessage")) {
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXDISCONNECT + ChatColor.RED + " « " + ChatColor.GRAY + player.getName());
            e.setQuitMessage(quitMessage);
        }else {
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXDISCONNECT + ChatColor.RED + " « " + ChatColor.GRAY + player.getName());
            e.setQuitMessage(null);
        }

        YVtils.getInstance().godmode.remove(player.getUniqueId());
        YVtils.getInstance().getRecentMessages().remove(e.getPlayer().getUniqueId());
    }
}