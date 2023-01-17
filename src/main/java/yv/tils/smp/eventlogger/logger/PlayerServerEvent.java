package yv.tils.smp.eventlogger.logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import yv.tils.smp.eventlogger.Logger;

import java.io.IOException;

/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class PlayerServerEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        try {
            new Logger().writer("PlayerJoin: " + p.getName() + " has joined the Server", "PlayerServer");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        try {
            new Logger().writer("PlayerQuit: " + p.getName() + " has disconnected the Server", "PlayerServer");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        String deathmessage = e.getDeathMessage();

        try {
            new Logger().writer("PlayerDeath: " + p.getName() + " has died - DeathMessage: " + deathmessage, "PlayerServer");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
