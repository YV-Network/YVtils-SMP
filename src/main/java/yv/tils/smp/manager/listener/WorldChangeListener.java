package yv.tils.smp.manager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import yv.tils.smp.manager.commands.Fly;
import yv.tils.smp.mods.admin.vanish.Vanish;
import yv.tils.smp.mods.other.SpawnElytra;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class WorldChangeListener implements Listener {
    @EventHandler
    public void onWorldChangeEvent(PlayerChangedWorldEvent e) {
        SpawnElytra.getInstance().onWorldChange(e);
        new Fly().onWorldChange(e);
        new Vanish().onWorldChange(e);
    }
}