package yv.tils.smp.listeners;

import yv.tils.smp.Main;
import yv.tils.smp.Placeholder.MessagePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collections;
import java.util.List;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        List<String> list2 = Main.getInstance().getConfig().getStringList("QuitMessage");

        for(int i = 0; i<list2.size(); i++){
            list2.set(i, list2.get(i).replace("player", playerName));
        }

        Collections.shuffle(list2);
        String Quitm = list2.get(0);

        event.setQuitMessage(Quitm);
        Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXDISCONNECT + ChatColor.RED + " « " + ChatColor.GRAY + player.getName());
    }
}
