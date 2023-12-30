package yv.tils.smp.mods.admin.logger.logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import yv.tils.smp.mods.admin.logger.Logger;

import java.io.IOException;
import java.util.List;

/**
 * @version 4.6.7
 * @since 4.6.7
 */
public class BlockInteract implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Location location = e.getBlock().getLocation();
        Material block = e.getBlock().getType();

        try {
            new Logger().writer("BlockBreakEvent: " + player.getName() + " has broken " + block + " at X: " + location.getBlockX() + " Y: " + location.getBlockY() + " Z: " + location.getBlockZ(), "BlockInteractEvent");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @EventHandler
    public void onExplosion(BlockExplodeEvent e) {
        List<Block> blocklist = e.blockList();
        Location location = e.getBlock().getLocation();
        Material block = e.getBlock().getType();

        try {
            new Logger().writer("BlockExplodeEvent: " + block + " exploded at X: " + location.getBlockX() + " Y: " + location.getBlockY() + " Z: " + location.getBlockZ() + " dropping following items " + blocklist, "BlockInteractEvent");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
