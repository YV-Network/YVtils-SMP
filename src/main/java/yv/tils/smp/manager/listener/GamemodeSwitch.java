package yv.tils.smp.manager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import yv.tils.smp.manager.commands.Fly;
import yv.tils.smp.mods.admin.vanish.Vanish;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class GamemodeSwitch implements Listener {
    @EventHandler
    public void onGamemodeSwitchEvent(PlayerGameModeChangeEvent e) {
        new Fly().onGamemodeSwitch(e);
        new Vanish().onGamemodeSwitch(e);
    }
}