package yv.tils.smp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import yv.tils.smp.YVtils;
import yv.tils.smp.internalapi.Log;
import yv.tils.smp.utils.configs.ConfigModeration;

import java.io.File;

/**
 * @since 4.6.6
 * @version 4.6.8
 */
public class ChatListener implements Listener {

    File statussavefile = new File(YVtils.getInstance().getDataFolder(), "StatusSave.yml");

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {

        String name = e.getPlayer().getName();
        String message = e.getMessage();

        if (e.getPlayer().hasPermission("yvtils.smp.collercodes.chat")) {
                message = ChatColor.translateAlternateColorCodes('&', message);
        }

        new Log(String.valueOf(e.getPlayer().getUniqueId()));

        if (!YVtils.getInstance().globalmute) {
            YamlConfiguration statussave = YamlConfiguration.loadConfiguration(statussavefile);
            if (statussave.get(String.valueOf(e.getPlayer().getUniqueId())) != null) {
                new Log("StatusModuleDebug - OnChat - With Check - With Prefix");
                String args = new ConfigModeration().ConfigRequest("StatusSave").getString(String.valueOf(e.getPlayer().getUniqueId()));
                String args1 = ChatColor.translateAlternateColorCodes('&', args);
                String prefix = ChatColor.translateAlternateColorCodes('&', args1) + " ";

                e.setCancelled(true);
                Bukkit.broadcastMessage(prefix + name + "§8: §f" + message);
            }else {
                new Log("StatusModuleDebug - OnChat - With Check - No Prefix");
                e.setCancelled(true);
                Bukkit.broadcastMessage(name + "§8: §f" + message);
            }
        }
    }
}