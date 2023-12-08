package yv.tils.smp.manager.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import yv.tils.smp.YVtils;
import yv.tils.smp.internalapi.Log;
import yv.tils.smp.manager.commands.GlobalMute;
import yv.tils.smp.utils.color.HexSupport;
import yv.tils.smp.utils.configs.status.StatusConfigManager;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        coloredChat(e);
        new GlobalMute().onChat(e);
    }

    private void coloredChat(AsyncPlayerChatEvent e) {
        String name = e.getPlayer().getName();
        String message = e.getMessage();

        if (e.getPlayer().hasPermission("yvtils.smp.collercodes.chat")) {
            message = HexSupport.hex(message);
        }

        new Log(String.valueOf(e.getPlayer().getUniqueId()));

        if (!GlobalMute.globalmute) {
            if (new StatusConfigManager().SavedRequest().get(String.valueOf(e.getPlayer().getUniqueId())) != null) {
                new Log("StatusModuleDebug - OnChat - With Check - With Prefix");
                String args = new StatusConfigManager().SavedRequest().getString(String.valueOf(e.getPlayer().getUniqueId()));
                String args1 = HexSupport.hex(args);
                String prefix = HexSupport.hex(args1) + " ";

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