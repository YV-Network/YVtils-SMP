package yv.tils.smp.manager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import yv.tils.smp.mods.other.SpawnElytra;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class AirTimeListener implements Listener {
    @EventHandler
    public void onToggleGlideEvent(EntityToggleGlideEvent e) {
        SpawnElytra.getInstance().onToggleGlide(e);
    }

    @EventHandler
    public void onToggleFlightEvent(PlayerToggleFlightEvent e) {
        SpawnElytra.getInstance().onDoubleJump(e);
    }
}