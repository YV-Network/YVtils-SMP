package yv.tils.smp.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.AnnouncementPlaceholder;
import yv.tils.smp.placeholder.MessagePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.Objects;

public class ConfigVersionUpdateChecker implements Listener {

    int i = Integer.parseInt(AnnouncementPlaceholder.CONFIGVERSION);
    File file2 = new File(SMPPlugin.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration modifyFile2 = YamlConfiguration.loadConfiguration(file2);

    public ConfigVersionUpdateChecker() {
        if (SMPPlugin.getInstance().getConfig().getInt("ConfigVersion") != i) {
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " A new Config Update is Available! ");
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " Save your edits and then delete the YVtils-BA Folder and restart the Server. After the restart, you can now transfer the Changes!");
            if (Objects.equals(LicenseCode.OLDPREMIUM, SMPPlugin.getInstance().getConfig().getString("License")))
                Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXTHANKS + "May the PREMIUM KEY has changed. Please look in your Config and compare it with this:" + LicenseCode.PREMIUM + " When it is not the same change it.");
        }
    }

    @EventHandler
    public void PlayerAnnounce(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.isOp()) {
            if (modifyFile2.getBoolean("MissingLanguage")) {
                player.sendMessage(MessagePlaceholder.PREFIXERROR + " This language is not available in the Moment! Help to translate: " + "https://discord.com/invite/y6uJYzdHc5");
                modifyFile2.set("MissingLanguage" , false);
            }
            if (SMPPlugin.getInstance().getConfig().getInt("ConfigVersion") != i) {
                if (player.hasPermission("yvtils.ba.updateannounce") || player.isOp()) {
                    player.sendMessage(MessagePlaceholder.PREFIXUPDATE + ChatColor.YELLOW + " A new Config Update is Available! For more Informations look in the Console!");
                    if (modifyFile2.getBoolean("MissingLanguage")) {
                        player.sendMessage(MessagePlaceholder.PREFIXERROR + " This language is not aviable in the Moment! Help to translate: ");
                    }}}}}}