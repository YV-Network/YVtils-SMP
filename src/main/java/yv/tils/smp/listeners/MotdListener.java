package yv.tils.smp.listeners;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.logger.ConsoleLog;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class MotdListener implements Listener {

    File file2 = new File(SMPPlugin.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration dontedit = YamlConfiguration.loadConfiguration(file2);

    @EventHandler
    public void onPing(ServerListPingEvent e) {

        new ConsoleLog(dontedit.getString("MaintenanceMode") + "");

        if (SMPPlugin.getInstance().maintenances) {
            e.setMotd(SMPPlugin.getInstance().getConfig().getString("MOTD'sText.Mainteance"));
            e.setMaxPlayers(0);
        }else {
            List<String> list = SMPPlugin.getInstance().getConfig().getStringList("Players");
            List<String> list1 = SMPPlugin.getInstance().getConfig().getStringList("MOTD'sText.Top");
            List<String> list2 = SMPPlugin.getInstance().getConfig().getStringList("MOTD'sText.Bottom");
            Collections.shuffle(list1);
            String top = list1.get(0);
            Collections.shuffle(list);
            String player1 = list.get(0);
            String player2 = list.get(1);

            list1.replaceAll(s -> s.replace("player1", player1).replace("player2", player2));
            list2.replaceAll(s -> s.replace("player1", player1).replace("player2", player2));

            Collections.shuffle(list2);
            String bottom = list2.get(0);

            e.setMotd(top + "\n" + bottom);
            e.setMaxPlayers(SMPPlugin.getInstance().getConfig().getInt("FakePlayerAllowedToJoinCounter"));
        }}}