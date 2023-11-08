package yv.tils.smp.mods.sit;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;
import yv.tils.smp.YVtils;

/**
 * @since 4.6.7
 * @version 4.6.8
 */
public class DismountListener implements Listener {
    private static final SitManager sitManager = YVtils.sitManager;
    @EventHandler
    public void onDismount(EntityDismountEvent e) {
        if (!(e.getEntity() instanceof Player player)) {
            return;
        }
        if (!(e.getDismounted() instanceof ArmorStand sit)) {
            return;
        }

        sitManager.standUp(player, sit, 0, 1.6, 0);
    }
}
