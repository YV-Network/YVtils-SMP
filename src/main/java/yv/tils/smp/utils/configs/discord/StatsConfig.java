package yv.tils.smp.utils.configs.discord;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.SMPPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class StatsConfig {
    File file = new File(SMPPlugin.getInstance().getDataFolder() + "/discord", "stats.yml");
    YamlConfiguration ymlfile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {
        ymlfile.addDefault("INFO", "DONT EDIT THIS FILE!");
        ymlfile.addDefault("GuildID", "ChannelIDs");
        //ymlfile.addDefault("774727929141657600", "1027941442477359154,1027941448722694204,1027941434105532516");

        ymlfile.options().copyDefaults(true);
        fileSave();
    }

    public void fileSave() {
        try {
            ymlfile.save(file);
        } catch (IOException e) {
            System.out.println("-------");
            Bukkit.getConsoleSender().sendMessage("File creation Error! Please get Support on the YVtils Support Discord");
            System.out.println("-------");
        }
    }
}