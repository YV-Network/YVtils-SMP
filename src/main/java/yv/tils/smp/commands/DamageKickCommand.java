package yv.tils.smp.commands;

import yv.tils.smp.SMPPlugin;
import yv.tils.smp.utils.LicenseCode;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DamageKickCommand implements CommandExecutor, Listener {

    private final List<UUID> kick = new ArrayList<>();
    int PlayerMove = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();
    if (sender.hasPermission("yvtils.smp.command.afkdamage")) {
        if (!kick.contains(uuid)) {
            kick.add(uuid);
            sender.sendMessage("Now you get kicked when you get Damage!");
            if (Objects.equals(LicenseCode.YVSMP, SMPPlugin.getInstance().getConfig().getString("License"))) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " parent set afk");
            }
        } else {
            kick.remove(uuid);
            PlayerMove = 0;
            sender.sendMessage("Now you don't get kicked when you get Damage!");
        }
    }
}
        return false;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            UUID uuid = player.getUniqueId();
            if (kick.contains(uuid)) {
                kick.remove(uuid);
                player.kickPlayer("You got kicked by your Safe Settings! Setting: KickOnDamageEvent \n Activate it again on next Join with /afkdamage");
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (kick.contains(e.getPlayer().getUniqueId())) {
            kick.remove(e.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (kick.contains(e.getPlayer().getUniqueId())) {
            PlayerMove++;
            if (PlayerMove > 10) {
                e.getPlayer().kickPlayer("Moved to often while AFK with Safe Setting 'KickOnDamageEvent' on!");
                PlayerMove = 0;
            }
            e.getPlayer().sendMessage("§x§f§b§0§0§0§0D§x§f§b§0§1§0§3o§x§f§b§0§1§0§6n§x§f§b§0§2§0§9'§x§f§b§0§3§0§bt §x§f§b§0§4§0§em§x§f§b§0§4§1§1o§x§f§b§0§5§1§4v§x§f§b§0§6§1§7e §x§f§b§0§6§1§aw§x§f§b§0§7§1§ch§x§f§b§0§8§1§fi§x§f§b§0§9§2§2l§x§f§b§0§9§2§5e §x§f§b§0§a§2§8A§x§f§b§0§b§2§bF§x§f§c§0§b§2§dK §x§f§c§0§c§3§0o§x§f§c§0§d§3§3r §x§f§c§0§d§3§6d§x§f§c§0§e§3§9e§x§f§c§0§f§3§ca§x§f§c§1§0§3§ec§x§f§c§1§0§4§1t§x§f§c§1§1§4§4i§x§f§c§1§2§4§7v§x§f§c§1§2§4§aa§x§f§c§1§3§4§dt§x§f§c§1§4§4§fe §x§f§c§1§5§5§2y§x§f§c§1§5§5§5o§x§f§c§1§6§5§8u§x§f§c§1§7§5§br §x§f§c§1§7§5§eS§x§f§c§1§8§6§1a§x§f§c§1§9§6§3f§x§f§c§1§a§6§6e §x§f§c§1§a§6§9S§x§f§c§1§b§6§ce§x§f§c§1§c§6§ft§x§f§c§1§c§7§2t§x§f§c§1§d§7§4i§x§f§c§1§e§7§7n§x§f§c§1§f§7§ag §x§f§c§1§f§7§d'§x§f§c§2§0§8§0K§x§f§c§2§1§8§3i§x§f§d§2§1§8§5c§x§f§d§2§2§8§8k§x§f§d§2§3§8§bO§x§f§d§2§3§8§en§x§f§d§2§4§9§1D§x§f§d§2§5§9§4a§x§f§d§2§6§9§6m§x§f§d§2§6§9§9a§x§f§d§2§7§9§cg§x§f§d§2§8§9§fe§x§f§d§2§8§a§2E§x§f§d§2§9§a§5v§x§f§d§2§a§a§7e§x§f§d§2§b§a§an§x§f§d§2§b§a§dt§x§f§d§2§c§b§0'");
            e.setCancelled(true);
        }
    }
}
