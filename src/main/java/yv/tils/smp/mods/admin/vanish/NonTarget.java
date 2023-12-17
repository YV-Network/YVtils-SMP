package yv.tils.smp.mods.admin.vanish;

import org.bukkit.event.entity.EntityTargetEvent;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class NonTarget {
    public void onMobTarget(EntityTargetEvent e) {
        if (e.getTarget() == null) return;
        if (Vanish.vanish.containsKey(e.getTarget().getUniqueId())) {
            e.setCancelled(true);
        }
    }
}