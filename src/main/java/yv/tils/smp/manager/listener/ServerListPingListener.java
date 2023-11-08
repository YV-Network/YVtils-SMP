package yv.tils.smp.manager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import yv.tils.smp.mods.server.motd.MOTDGenerator;

/**
 * @since CH2-1.0.0
 * @version CH2-1.0.0
 */
public class ServerListPingListener implements Listener {
    @EventHandler
    public void onServerListPingEvent(ServerListPingEvent e) {
        new MOTDGenerator().ServerListPingEvent(e);
    }
}