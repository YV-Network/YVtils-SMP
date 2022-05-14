package yv.tils.smp.modules.status;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.placeholder.ColorCode;
import yv.tils.smp.LanguageSystem.LanguagePlaceholder;
import yv.tils.smp.utils.ConfigModeration;

import java.io.File;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class JoinQuitStatus implements Listener {

    File statusmodulefile = new File(SMPPlugin.getInstance().getDataFolder(), "StatusModule.yml");
    YamlConfiguration statusmodule = YamlConfiguration.loadConfiguration(statusmodulefile);
    File statussavefile = new File(SMPPlugin.getInstance().getDataFolder(), "StatusSave.yml");
    YamlConfiguration statussave = YamlConfiguration.loadConfiguration(statussavefile);

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        new ConsoleLog("StatusModuleDebug - OnJoin - No Check");

        Team team = player.getScoreboard().getTeam(player.getName());
        new ConsoleLog(String.valueOf(team));

        if (team != null) {


            new NametagManager().addPlayer(player, new ColorCode().ColorCodes(new ConfigModeration().ConfigRequest("StatusSave").getString(String.valueOf(e.getPlayer().getUniqueId()))) + " ");


            new ConsoleLog("StatusModuleDebug - OnJoin - With Check");
            e.getPlayer().sendMessage(LanguagePlaceholder.DirectFormatter("You have the Status " + new ColorCode().ColorCodes(new ConfigModeration().ConfigRequest("StatusSave").getString(String.valueOf(e.getPlayer().getUniqueId()))) + " §fselected!", "Du hast den Status" + new ColorCode().ColorCodes(new ConfigModeration().ConfigRequest("StatusSave").getString(String.valueOf(e.getPlayer().getUniqueId()))) + " §fausgewählt!"));

            player.setDisplayName(new ColorCode().ColorCodes(new ConfigModeration().ConfigRequest("StatusSave").getString(String.valueOf(e.getPlayer().getUniqueId()))) + " " + player.getName());
            player.setPlayerListName(new ColorCode().ColorCodes(new ConfigModeration().ConfigRequest("StatusSave").getString(String.valueOf(e.getPlayer().getUniqueId()))) + " " + player.getName());}
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Team team = player.getScoreboard().getTeam(player.getName());

        if (team != null) {
            new NametagManager().removePlayer(player);
        }
    }
}
