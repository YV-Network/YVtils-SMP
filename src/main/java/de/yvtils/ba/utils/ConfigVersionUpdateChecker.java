package de.yvtils.ba.utils;

import de.yvtils.ba.Main;
import de.yvtils.ba.Placeholder.AnnouncementPlaceholder;
import de.yvtils.ba.Placeholder.MessagePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class ConfigVersionUpdateChecker implements Listener {

    int i = Integer.parseInt(AnnouncementPlaceholder.CONFIGVERSION);

    public ConfigVersionUpdateChecker() {
        if (Main.getInstance().getConfig().getInt("ConfigVersion") != i) {
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " A new Config Update is Available! ");
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " Save your edits and then delete the YVtils-BA Folder and restart the Server. After the restart, you can now transfer the Changes!");
            if (Objects.equals(LicenseCode.OLDPREMIUM, Main.getInstance().getConfig().getString("License")))
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXTHANKS + "May the PREMIUM KEY has changed. Please look in your Config and compare it with this:" + LicenseCode.PREMIUM + " When it is not the same change it.");
        }
    }

    @EventHandler
    public void PlayerAnnounce(PlayerJoinEvent event) {
        if (Main.getInstance().getConfig().getInt("ConfigVersion") != i) {
            Player player = event.getPlayer();
            if (player.hasPermission("yvtils.ba.updateannounce") || player.isOp()) {
            player.sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " A new Config Update is Available! For more Informations look in the Console!");
            }
        }
    }
}
