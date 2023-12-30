package yv.tils.smp.utils.configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.YVtils;

import java.io.File;
import java.io.IOException;

/**
 * Creates the Config Files for the Plugin, and give them Input with Config Options
 *
 * @version 4.6.8
 * @since 4.6.6
 * @deprecated
 */
public class ConfigModeration {

    File doNotEditFile = new File(YVtils.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration doNotEdit = YamlConfiguration.loadConfiguration(doNotEditFile);

    /**
     * Use all Config Methods!
     * You can use as Config 'Langauge; MinecraftDiscordBridge; DoNotEdit; WhitelistedDiscordPlayers; StatusModule; StatusSave
     *
     * @since 4.6.6
     */
    public YamlConfiguration ConfigRequest(String config) {
        File configFile = new File(YVtils.getInstance().getDataFolder(), config + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configFile);
        try {
            configuration.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public void onSave() {
        try {
            doNotEdit.save(doNotEditFile);
        } catch (IOException e) {
            System.out.println("-------");
            Bukkit.getConsoleSender().sendMessage("File creation Error! Please get Support on the YVtils Support Discord");
            System.out.println("-------");
        }
    }

}