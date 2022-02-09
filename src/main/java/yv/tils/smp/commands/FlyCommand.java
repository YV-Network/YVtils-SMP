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

public class FlyCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();
            if (sender.hasPermission("yvtils.smp.command.fly")) {
                if (!SMPPlugin.getInstance().fly.contains(uuid)) {
                    SMPPlugin.getInstance().fly.add(uuid);
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    sender.sendMessage("Fly is now enabled!");
                } else {
                    if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()) {
                        SMPPlugin.getInstance().fly1.add(uuid);
                        SMPPlugin.getInstance().fly.remove(uuid);
                        player.setAllowFlight(false);
                        sender.sendMessage("Fly is disabled!");
                    }else {
                        SMPPlugin.getInstance().fly.remove(uuid);
                        player.setAllowFlight(false);
                        sender.sendMessage("Fly is disabled!");
                    }
                }
            }
        }
        return true;
    }

    @EventHandler
    public void onLandDamage(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER
                && (event.getCause() == EntityDamageEvent.DamageCause.FALL)
                && SMPPlugin.getInstance().fly1.contains(event.getEntity().getUniqueId())) {
            event.setCancelled(true);
            UUID uuid = event.getEntity().getUniqueId();
            SMPPlugin.getInstance().fly1.remove(uuid);
        }
    }
}