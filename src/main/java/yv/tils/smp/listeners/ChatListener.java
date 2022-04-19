package yv.tils.smp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.block.data.type.Snow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import yv.tils.smp.placeholder.ColorCode;

public class ChatListener implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        Boolean s0 = e.getMessage().contains("&0");
        Boolean s1 = e.getMessage().contains("&1");
        Boolean s2= e.getMessage().contains("&2");
        Boolean s3 = e.getMessage().contains("&3");
        Boolean s4 = e.getMessage().contains("&4");
        Boolean s5 = e.getMessage().contains("&5");
        Boolean s6 = e.getMessage().contains("&6");
        Boolean s7 = e.getMessage().contains("&7");
        Boolean s8 = e.getMessage().contains("&8");
        Boolean s9 = e.getMessage().contains("&9");
        Boolean sa = e.getMessage().contains("&a");
        Boolean sb = e.getMessage().contains("&b");
        Boolean sc = e.getMessage().contains("&c");
        Boolean sd = e.getMessage().contains("&d");
        Boolean se = e.getMessage().contains("&e");
        Boolean sf = e.getMessage().contains("&f");
        Boolean sk = e.getMessage().contains("&k");
        Boolean sl = e.getMessage().contains("&l");
        Boolean sm = e.getMessage().contains("&m");
        Boolean sn = e.getMessage().contains("&n");
        Boolean so = e.getMessage().contains("&o");
        Boolean sr = e.getMessage().contains("&r");

        if (e.getPlayer().hasPermission("yvtils.smp.collercodes.chat"))
        if (s0 || s1 || s2 || s3 || s4 || s5 || s6 || s7 || s8 || s9 || sa || sb || sc || sd || se || sf || sk || sl || sm || sn || so || sr) {
            e.setMessage(new ColorCode().ColorCodes(e.getMessage()));
        }
    }
}
