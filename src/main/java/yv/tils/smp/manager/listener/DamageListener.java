package yv.tils.smp.manager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import yv.tils.smp.manager.commands.Fly;
import yv.tils.smp.manager.commands.God;
import yv.tils.smp.mods.other.SpawnElytra;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class DamageListener implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        SpawnElytra.getInstance().onLandDamage(e);
        new Fly().onLandingDamage(e);
        new God().onDamage(e);
    }
}