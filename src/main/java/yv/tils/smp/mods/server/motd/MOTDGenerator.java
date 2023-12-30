package yv.tils.smp.mods.server.motd;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.server.ServerListPingEvent;
import yv.tils.smp.YVtils;
import yv.tils.smp.manager.commands.Maintenance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class MOTDGenerator {
    public void ServerListPingEvent(ServerListPingEvent e) {
        if (Maintenance.maintenance) {
            e.setMotd(YVtils.getInstance().getConfig().getString("MOTD'sText.Maintenance"));
            e.setMaxPlayers(0);
        } else {

            List<String> players = new ArrayList<>();
            for (OfflinePlayer player : Bukkit.getWhitelistedPlayers()) {
                players.add(player.getName());
            }

            List<String> topText = YVtils.getInstance().getConfig().getStringList("MOTD'sText.Top");
            List<String> bottomText = YVtils.getInstance().getConfig().getStringList("MOTD'sText.Bottom");

            e.setMotd(generateString(players, topText) + "\n" + generateString(players, bottomText));
            e.setMaxPlayers(YVtils.getInstance().getConfig().getInt("FakePlayerAllowedToJoinCounter"));
        }
    }

    private String generateString(List<String> players, List<String> MOTDs) {
        Random random = new Random();
        String MOTD = MOTDs.get(random.nextInt(MOTDs.size()));

        List<String> placeholders = new ArrayList<>();
        for (int i = 1; i <= players.size(); i++) {
            placeholders.add("player" + i);
        }

        for (String placeholder : placeholders) {
            List<String> unusedNames = new ArrayList<>(players);

            for (String playerName : players) {
                if (MOTD.contains(playerName)) {
                    unusedNames.remove(playerName);
                }
            }

            String randomPlayer = unusedNames.get(random.nextInt(unusedNames.size()));
            MOTD = MOTD.replaceAll(placeholder, randomPlayer);
        }

        return MOTD;
    }

}