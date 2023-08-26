package yv.tils.smp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.MessagePlaceholder;

import java.util.Collections;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        List<String> list2 = SMPPlugin.getInstance().getConfig().getStringList("QuitMessage");

        list2.replaceAll(s -> s.replace("player", playerName));

        Collections.shuffle(list2);
        String Quitm = list2.get(0);

        if (SMPPlugin.getInstance().vanished.contains(player.getUniqueId())) {
            SMPPlugin.getInstance().vanished.remove(player.getUniqueId());
            event.setQuitMessage(null);
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXDISCONNECT + ChatColor.RED + "Vanish « " + ChatColor.GRAY + player.getName());
        } else {
            if (SMPPlugin.getInstance().getConfig().getBoolean("SendQuitMessage")) {
                event.setQuitMessage(Quitm);
                Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXDISCONNECT + ChatColor.RED + " « " + ChatColor.GRAY + player.getName());
            }else {

                event.setQuitMessage(null);
                Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXDISCONNECT + ChatColor.RED + " « " + ChatColor.GRAY + player.getName());
            }}
        SMPPlugin.getInstance().godmode.remove(player.getUniqueId());
        SMPPlugin.getInstance().getRecentMessages().remove(event.getPlayer().getUniqueId());
        //new StatsDescription().editDesc();
    }
}
