package yv.tils.smp.mods.server.connect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import yv.tils.smp.YVtils;
import yv.tils.smp.placeholder.MessagePlaceholder;

import java.util.Collections;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class PlayerJoin {
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();
        List<String> list = YVtils.getInstance().getConfig().getStringList("JoinMessage");

        list.replaceAll(s -> s.replace("player", playerName));

        Collections.shuffle(list);
        String joinMessage = list.get(0);

        if (YVtils.getInstance().getConfig().getBoolean("SendJoinMessage")) {
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXCONNECT + ChatColor.GREEN + " » " + ChatColor.GRAY + player.getName());
            e.setJoinMessage(joinMessage);
        }else {
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXCONNECT + ChatColor.GREEN + " » " + ChatColor.GRAY + player.getName());
            e.setJoinMessage(null);
        }
    }
}