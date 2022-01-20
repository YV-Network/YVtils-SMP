package de.yvtils.ba.listeners;

import de.yvtils.ba.Main;
import de.yvtils.ba.Placeholder.MessagePlaceholder;
import de.yvtils.ba.utils.LicenseCode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    }
}

