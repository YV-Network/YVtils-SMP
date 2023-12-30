package yv.tils.smp.mods.server.mainteance;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;
import yv.tils.smp.manager.commands.Maintenance;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class PlayerJoin {
    public void playerLoginEvent(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        if (Maintenance.maintenance && !player.hasPermission("yvtils.smp.maintenance.join")) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, LanguageFile.getMessage(LanguageMessage.MAINTENANCE_PLAYER_NOT_ALLOWED_TO_JOIN_KICK_MESSAGE));
        }
    }
}