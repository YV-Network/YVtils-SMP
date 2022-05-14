package yv.tils.smp.commands;

import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import yv.tils.smp.SMPPlugin;

import java.util.UUID;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class GodCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            UUID uuid = player.getUniqueId();
            if (sender.hasPermission("yvtils.smp.command.god")) {
                if (!SMPPlugin.getInstance().godmode.contains(uuid)) {
                    SMPPlugin.getInstance().godmode.add(uuid);
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    sender.sendMessage("God Mode is now enabled!");
                } else {
                    if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()) {
                        SMPPlugin.getInstance().godmode1.add(uuid);
                        SMPPlugin.getInstance().godmode.remove(uuid);
                        player.setAllowFlight(false);
                        sender.sendMessage("God Mode is now disabled!");
                    }else {
                        SMPPlugin.getInstance().godmode.remove(uuid);
                        player.setAllowFlight(false);
                        sender.sendMessage("God Mode is now disabled!");
                    }
                }
            }
        }
        return true;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (SMPPlugin.getInstance().godmode.contains(player.getUniqueId())){
            event.setCancelled(true);
        }
        if (event.getEntityType() == EntityType.PLAYER
                && (event.getCause() == EntityDamageEvent.DamageCause.FALL)
                && SMPPlugin.getInstance().godmode1.contains(event.getEntity().getUniqueId())) {
            event.setCancelled(true);
            UUID uuid = event.getEntity().getUniqueId();
            SMPPlugin.getInstance().godmode1.remove(uuid);
        }}}}