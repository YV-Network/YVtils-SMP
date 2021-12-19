package de.yvtils.ba.listeners;

import de.yvtils.ba.Main;
import de.yvtils.ba.Placeholder.MessagePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collections;
import java.util.List;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        List<String> list2 = Main.getInstance().getConfig().getStringList("JoinMessage");

        for (int i = 0; i < list2.size(); i++) {
            list2.set(i, list2.get(i).replace("player", playerName));
        }

        Collections.shuffle(list2);
        String joinm = list2.get(0);

        event.setJoinMessage(joinm);
        Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXCONNECT + ChatColor.GREEN + " » " + ChatColor.GRAY + player.getName());
        if (Main.getInstance().getConfig().getBoolean("EventJoinTitle")) {
            player.sendTitle("§x§f§f§f§f§f§f§oM§x§f§f§e§b§f§4§oe§x§f§f§d§8§e§9§or§x§f§f§c§4§d§f§or§x§f§f§b§1§d§4§oy §x§f§f§9§d§c§9§oC§x§f§f§8§9§b§e§oh§x§f§f§7§6§b§4§or§x§f§f§6§2§a§9§oi§x§f§f§4§e§9§e§os§x§f§f§3§b§9§3§ot§x§f§f§2§7§8§9§om§x§f§f§1§4§7§e§oa§x§f§f§0§0§7§3§os", "§x§0§0§b§a§f§fW§x§0§1§b§4§f§fi§x§0§2§a§f§f§fs§x§0§3§a§9§f§fh §x§0§3§a§3§f§fy§x§0§4§9§e§f§fo§x§0§5§9§8§f§fu §x§0§6§9§3§f§ft§x§0§7§8§d§f§fh§x§0§8§8§7§f§fe §x§0§8§8§2§f§fY§x§0§9§7§c§f§fV§x§0§a§7§6§f§ft§x§0§b§7§1§f§fi§x§0§c§6§b§f§fl§x§0§d§6§5§f§fs§x§0§e§6§0§f§f-§x§0§e§5§a§f§fB§x§0§f§5§5§f§fA §x§1§0§4§f§f§fD§x§1§1§4§9§f§fe§x§1§2§4§4§f§fv§x§1§3§3§e§f§fe§x§1§4§3§8§f§fl§x§1§4§3§3§f§fo§x§1§5§2§d§f§fp§x§1§6§2§7§f§fm§x§1§7§2§2§f§fe§x§1§8§1§c§f§fn§x§1§9§1§7§f§ft §x§1§9§1§1§f§fT§x§1§a§0§b§f§fe§x§1§b§0§6§f§fa§x§1§c§0§0§f§fm", 20 , 50, 20);
        }else {
            player.sendMessage("§x§f§f§f§f§f§f§oM§x§f§f§e§b§f§4§oe§x§f§f§d§8§e§9§or§x§f§f§c§4§d§f§or§x§f§f§b§1§d§4§oy §x§f§f§9§d§c§9§oC§x§f§f§8§9§b§e§oh§x§f§f§7§6§b§4§or§x§f§f§6§2§a§9§oi§x§f§f§4§e§9§e§os§x§f§f§3§b§9§3§ot§x§f§f§2§7§8§9§om§x§f§f§1§4§7§e§oa§x§f§f§0§0§7§3§os\n §x§0§0§b§a§f§ff§x§0§1§b§4§f§fr§x§0§2§a§e§f§fo§x§0§3§a§7§f§fm §x§0§4§a§1§f§ft§x§0§5§9§b§f§fh§x§0§6§9§5§f§fe §x§0§7§8§f§f§fY§x§0§7§8§8§f§fV§x§0§8§8§2§f§ft§x§0§9§7§c§f§fi§x§0§a§7§6§f§fl§x§0§b§7§0§f§fs§x§0§c§6§9§f§f-§x§0§d§6§3§f§fB§x§0§e§5§d§f§fA §x§0§f§5§7§f§fD§x§1§0§5§1§f§fe§x§1§1§4§a§f§fv§x§1§2§4§4§f§fe§x§1§3§3§e§f§fl§x§1§4§3§8§f§fo§x§1§5§3§2§f§fp§x§1§5§2§b§f§fm§x§1§6§2§5§f§fe§x§1§7§1§f§f§fn§x§1§8§1§9§f§ft §x§1§9§1§3§f§fT§x§1§a§0§c§f§fe§x§1§b§0§6§f§fa§x§1§c§0§0§f§fm");
        }
    }
}

