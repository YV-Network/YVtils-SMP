package yv.tils.smp.mods.admin.vanish;

import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityTargetEvent;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class NonTarget {
    public void onMobTarget(EntityTargetEvent e) {

        if (e.getEntity().equals(EntityType.WARDEN)) {
            e.setTarget(null);
            e.setCancelled(true);
        }

        if (e.getTarget() == null) return;
        if (Vanish.mobTarget.containsKey(e.getTarget().getUniqueId()) && Vanish.mobTarget.get(e.getTarget().getUniqueId())) {
            e.setCancelled(true);
        }
    }
}