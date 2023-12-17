package yv.tils.smp.manager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import yv.tils.smp.mods.admin.vanish.NonTarget;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class EntityTargetListener implements Listener {
    @EventHandler
    public void onEvent(EntityTargetEvent e) {
        new NonTarget().onMobTarget(e);
    }
}