package yv.tils.smp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.placeholder.ColorCode;
import yv.tils.smp.utils.configs.ConfigModeration;

import java.io.File;

/**
 * @since 4.6.6
 * @version 4.6.8
 */
public class ChatListener implements Listener {

    File statussavefile = new File(SMPPlugin.getInstance().getDataFolder(), "StatusSave.yml");

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {

        String name = e.getPlayer().getName();
        String message = e.getMessage();

        if (e.getPlayer().hasPermission("yvtils.smp.collercodes.chat")) {
                message = ChatColor.translateAlternateColorCodes('&', message);
        }

        new ConsoleLog(String.valueOf(e.getPlayer().getUniqueId()));

        if (!SMPPlugin.getInstance().globalmute) {
            YamlConfiguration statussave = YamlConfiguration.loadConfiguration(statussavefile);
            if (statussave.get(String.valueOf(e.getPlayer().getUniqueId())) != null) {
                new ConsoleLog("StatusModuleDebug - OnChat - With Check - With Prefix");
                String prefix = new ColorCode().ColorCodes(new ColorCode().ColorCodes(new ConfigModeration().ConfigRequest("StatusSave").getString(String.valueOf(e.getPlayer().getUniqueId())))) + " ";

                e.setCancelled(true);
                Bukkit.broadcastMessage(prefix + name + "§8: §f" + message);
            }else {
                new ConsoleLog("StatusModuleDebug - OnChat - With Check - No Prefix");
                e.setCancelled(true);
                Bukkit.broadcastMessage(name + "§8: §f" + message);
            }}}}