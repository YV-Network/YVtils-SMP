package yv.tils.smp.manager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import yv.tils.smp.mods.server.mainteance.PlayerJoin;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class LoginListener implements Listener {
    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent e) {
        new PlayerJoin().playerLoginEvent(e);
    }
}