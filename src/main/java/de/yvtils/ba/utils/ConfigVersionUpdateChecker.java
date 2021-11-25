package de.yvtils.ba.utils;

import de.yvtils.ba.Main;
import de.yvtils.ba.Placeholder.MessagePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConfigVersionUpdateChecker implements Listener {

    int i = 4;

    public ConfigVersionUpdateChecker() {
        if (Main.getInstance().getConfig().getInt("ConfigVersion") != i) {
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " A new Config Update is Available! ");
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " Save your edits and then delete the YVtils-BA Folder and restart the Server. After the restart, you can now transfer the Changes!");
        }
    }

    @EventHandler
    public void Test(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Main.getInstance().getConfig().getInt("ConfigVersion") != i) {
            if (player.hasPermission("yvtils.ba.updateannounce") || player.isOp()) {
            player.sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " A new Config Update is Available! For more Informations look in the Console!");
            }
        }
    }
}
