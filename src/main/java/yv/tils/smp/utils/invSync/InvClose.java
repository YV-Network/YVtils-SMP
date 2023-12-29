package yv.tils.smp.utils.invSync;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class InvClose {
    public void invClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        if (InvOpen.invOpen.containsKey(player.getUniqueId()) && InvOpen.invOpen.get(player.getUniqueId())) {
            InvOpen.invOpen.remove(player.getUniqueId());
            InvOpen.inventory.remove(player.getUniqueId());
            InvOpen.containerLocation.remove(player.getUniqueId());
        }
    }
}