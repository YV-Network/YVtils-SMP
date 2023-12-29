package yv.tils.smp.manager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import yv.tils.smp.manager.commands.Fly;
import yv.tils.smp.mods.admin.vanish.Vanish;
import yv.tils.smp.mods.server.connect.PlayerJoin;
import yv.tils.smp.utils.updater.VersionGetter;

/**
 * @since 4.6.6
 * @version CH2-1.0.0
 */
public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        new VersionGetter().onPlayerJoin(e.getPlayer());
        new PlayerJoin().onPlayerJoin(e);
        new Fly().onRejoin(e);
        new Vanish().onRejoin(e);
    }
}