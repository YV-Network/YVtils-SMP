package yv.tils.smp.modules.status;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;
import yv.tils.smp.utils.language.LanguageFile;
import yv.tils.smp.utils.language.LanguageMessage;
import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.placeholder.ColorCode;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.ConfigModeration;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class JoinQuitStatus implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        new ConsoleLog("StatusModuleDebug - OnJoin - No Check");

        Team team = player.getScoreboard().getTeam(player.getName());
        new ConsoleLog(String.valueOf(team));

        if (team != null) {
            new NametagManager().addPlayer(player, new ColorCode().ColorCodes(new ConfigModeration().ConfigRequest("StatusSave").getString(String.valueOf(e.getPlayer().getUniqueId()))) + " ");

            new ConsoleLog("StatusModuleDebug - OnJoin - With Check");

            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("STATUS");
            list2.add(new ColorCode().ColorCodes(new ConfigModeration().ConfigRequest("StatusSave").getString(String.valueOf(e.getPlayer().getUniqueId()))));
            e.getPlayer().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_SELECTED_STATUS_JOIN_ANNOUNCEMENT), list1, list2));
            player.setDisplayName(new ColorCode().ColorCodes(new ConfigModeration().ConfigRequest("StatusSave").getString(String.valueOf(e.getPlayer().getUniqueId()))) + " " + player.getName());
            player.setPlayerListName(new ColorCode().ColorCodes(new ConfigModeration().ConfigRequest("StatusSave").getString(String.valueOf(e.getPlayer().getUniqueId()))) + " " + player.getName());}
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PlayerStatusSave(e.getPlayer());
    }

    public void PlayerStatusSave(Player player) {
        Team team = player.getScoreboard().getTeam(player.getName());

        if (team != null) {
            new NametagManager().removePlayer(player);
        }
    }}