package yv.tils.smp.manager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import yv.tils.smp.mods.other.SpawnElytra;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class InventoryListener implements Listener {
    @EventHandler
    public void onSwapHandEvent(PlayerSwapHandItemsEvent e) {
        SpawnElytra.getInstance().onHandSwap(e);
    }
}