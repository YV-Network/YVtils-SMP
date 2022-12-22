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
import yv.tils.smp.utils.language.LanguageFile;
import yv.tils.smp.utils.language.LanguageMessage;
import yv.tils.smp.SMPPlugin;

import java.util.UUID;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class FlyCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            UUID uuid = player.getUniqueId();
            if (sender.hasPermission("yvtils.smp.command.fly")) {
                if (!SMPPlugin.getInstance().fly.contains(uuid)) {
                    SMPPlugin.getInstance().fly.add(uuid);
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_ENABLE));
                } else {
                    if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()) {
                        SMPPlugin.getInstance().fly1.add(uuid);
                        SMPPlugin.getInstance().fly.remove(uuid);
                        player.setAllowFlight(false);
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_DISABLE));
                    }else {
                        SMPPlugin.getInstance().fly.remove(uuid);
                        player.setAllowFlight(false);
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.FLY_COMMAND_DISABLE));
                    }}}}
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
        }}}