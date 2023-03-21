package yv.tils.smp.updateutils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import yv.tils.smp.Variables;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.updateutils.database.VersionChecker;
import yv.tils.smp.utils.configs.language.LanguageFile;

/**
 * @since 4.6.7
 * @version 4.6.7
 */
public class JoinAnnouncer implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPermission("yvtils.smp.update.getannounced")) {
            if (new VersionChecker().VersionChecker_FullRelease(new Variables().PluginVersion).equals("UA")) {
                e.getPlayer().sendMessage(LanguageFile.DirectFormatter( MessagePlaceholder.PREFIXUPDATE + " §fUpdate Available - Running Version: " + new Variables().PluginVersion + "; Available Version: " + new VersionChecker().NewestVersion(), MessagePlaceholder.PREFIXUPDATE + " §fUpdate Verfügbar - Nutzende Version: " + new Variables().PluginVersion + "; Neueste Version: " + new VersionChecker().NewestVersion()));
            }
        }
    }
}
