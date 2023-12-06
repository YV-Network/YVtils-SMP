package yv.tils.smp.mods.status;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;
import yv.tils.smp.internalapi.Log;
import yv.tils.smp.internalapi.StringReplacer;
import yv.tils.smp.utils.color.HexSupport;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.configs.status.StatusConfigManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version CH2-1.0.0
 */
public class JoinQuitStatus implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        new Log("StatusModuleDebug - OnJoin - No Check");
        if (new StatusConfigManager().SavedRequest().contains(String.valueOf(e.getPlayer().getUniqueId()))) {
            String args = new StatusConfigManager().SavedRequest().getString(String.valueOf(e.getPlayer().getUniqueId()));
            String status = HexSupport.hex(args);

            new NametagManager().addPlayer(player, status + " ");

            new Log("StatusModuleDebug - OnJoin - With Check");

            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            list1.add("STATUS");
            list2.add(status);

            player.setDisplayName(status + " " + player.getName());
            player.setPlayerListName(status + " " + player.getName());

            e.getPlayer().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_SELECTED_STATUS_JOIN_ANNOUNCEMENT), list1, list2));
        }
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