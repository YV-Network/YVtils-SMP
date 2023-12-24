package yv.tils.smp.manager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import yv.tils.smp.mods.admin.moderation.altCommands;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class CommandPreprocess implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e) {
        new altCommands().onPlayerBanCommandPreProcess(e);
    }
}