package yv.tils.smp.listeners;

import yv.tils.smp.SMPPlugin;
import yv.tils.smp.Placeholder.MessagePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collections;
import java.util.List;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        List<String> list2 = SMPPlugin.getInstance().getConfig().getStringList("JoinMessage");

        for (int i = 0; i < list2.size(); i++) {
            list2.set(i, list2.get(i).replace("player", playerName));
        }


        Collections.shuffle(list2);
        String joinm = list2.get(0);

        if (SMPPlugin.getInstance().vanished.contains(player.getUniqueId())) {
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXCONNECT + ChatColor.GREEN + "Vanish » " + ChatColor.GRAY + player.getName());
            event.setJoinMessage(null);
        }else {
            if (SMPPlugin.getInstance().getConfig().getBoolean("SendJoinMessage")) {
                Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXCONNECT + ChatColor.GREEN + " » " + ChatColor.GRAY + player.getName());
                event.setJoinMessage(joinm);
            }else {
                event.setJoinMessage(null);
                Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXCONNECT + ChatColor.GREEN + " » " + ChatColor.GRAY + player.getName());
            }
        }
    }
}

