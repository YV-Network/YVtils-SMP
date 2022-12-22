package yv.tils.smp.eventlogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        System.out.println(e.getItem().getItemMeta() + " a");
        System.out.println(e.getItem().getItemMeta().getAsString() + " b");
    }
}
