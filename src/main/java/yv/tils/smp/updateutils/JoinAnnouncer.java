package yv.tils.smp.updateutils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import yv.tils.smp.Variables;
import yv.tils.smp.updateutils.database.VersionChecker;

/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class JoinAnnouncer implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPermission("yvtils.smp.update.getannounced")) {
            if (new VersionChecker().VersionChecker_FullRelease(new Variables().PluginVersion).equals("UA")) {
                e.getPlayer().sendMessage("Update Available - Running: 4.6.7; Available: 4.6.7");
            }
        }
    }
}
