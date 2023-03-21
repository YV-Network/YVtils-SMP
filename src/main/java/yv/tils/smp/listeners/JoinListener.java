package yv.tils.smp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.MessagePlaceholder;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        List<String> list = SMPPlugin.getInstance().getConfig().getStringList("JoinMessage");
        File file2 = new File(SMPPlugin.getInstance().getDataFolder(), "DoNotEdit.yml");
        YamlConfiguration dontedit = YamlConfiguration.loadConfiguration(file2);

        list.replaceAll(s -> s.replace("player", playerName));

        Collections.shuffle(list);
        String joinm = list.get(0);
        if (SMPPlugin.getInstance().maintenances) {
            if (event.getPlayer().hasPermission("yvtils.smp.maintenance.join")) {
                return;
            }else {
                event.getPlayer().kickPlayer(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_PLAYER_NOT_ALLOWED_TO_JOIN_KICK_MESSAGE));
            }}

        if (SMPPlugin.getInstance().vanished.contains(player.getUniqueId())) {
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXCONNECT + ChatColor.GREEN + "Vanish » " + ChatColor.GRAY + player.getName());
            event.setJoinMessage(null);
        }else {
            if (SMPPlugin.getInstance().getConfig().getBoolean("SendJoinMessage")) {
                Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXCONNECT + ChatColor.GREEN + " » " + ChatColor.GRAY + player.getName());
                event.setJoinMessage(joinm);
            }else {
                event.setJoinMessage(null);
                Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXCONNECT + ChatColor.GREEN + " » " + ChatColor.GRAY + player.getName());
            }}}}