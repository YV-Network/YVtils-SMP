package yv.tils.smp.listeners;

import yv.tils.smp.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.*;


public class MotdListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e) {

        List<String> list1 = Main.getInstance().getConfig().getStringList("MOTD'sText.Top");

        Collections.shuffle(list1);
        String top = list1.get(0);

                List<String> list = Main.getInstance().getConfig().getStringList("Players");

        Collections.shuffle(list);
        String player1 = list.get(0);
        String player2 = list.get(1);

        List<String> list2 = Main.getInstance().getConfig().getStringList("MOTD'sText.Bottom");

        for(int i = 0; i<list2.size(); i++){
            list2.set(i, list2.get(i).replace("player1", player1).replace("player2", player2));
        }

        Collections.shuffle(list2);
        String bottom = list2.get(0);

                e.setMotd(top + "\n" + bottom);

                e.setMaxPlayers(Main.getInstance().getConfig().getInt("FakePlayerAllowedToJoinCounter"));
        }

    }
