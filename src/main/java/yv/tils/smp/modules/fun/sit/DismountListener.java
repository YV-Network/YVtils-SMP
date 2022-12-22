package yv.tils.smp.modules.fun.sit;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;
import yv.tils.smp.utils.ServerStart_StopEvent;

public class DismountListener implements Listener {
    private static final SitManager sitManager = ServerStart_StopEvent.sitManager;
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
