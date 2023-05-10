package yv.tils.smp.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.Variables;
import yv.tils.smp.placeholder.MessagePlaceholder;

import java.io.File;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class ConfigVersionUpdateChecker implements Listener {

    int i = Integer.parseInt(Variables.CONFIGVERSION);
    File file2 = new File(SMPPlugin.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration modifyFile2 = YamlConfiguration.loadConfiguration(file2);

    @EventHandler
    public void PlayerAnnounce(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.isOp()) {
            if (modifyFile2.getBoolean("MissingLanguage")) {
                player.sendMessage(MessagePlaceholder.PREFIXERROR + " This language isn't available in the Moment! Help to translate: https://crowdin.com/project/yvtils-smp");
                modifyFile2.set("MissingLanguage" , false);
            }
            if (SMPPlugin.getInstance().getConfig().getInt("ConfigVersion") != i) {
                if (player.hasPermission("yvtils.smp.updateannounce") || player.isOp()) {
                    player.sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " A new Config Update is Available! For more Information's look in the Console!");
                    if (modifyFile2.getBoolean("MissingLanguage")) {
                        player.sendMessage(MessagePlaceholder.PREFIXERROR + " This language is not available in the Moment! Help to translate: https://crowdin.com/project/yvtils-smp");
                    }}}}}

    public ConfigVersionUpdateChecker() {
        if (SMPPlugin.getInstance().getConfig().getInt("ConfigVersion") != i) {
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " A new Config Update is Available! ");
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " Save your edits and then delete the config.yml File in the YVtils-SMP Folder and restart the Server. After the restart, you can now transfer the Changes!");
        }}}