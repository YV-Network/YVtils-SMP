package yv.tils.smp.utils.configs.discord;

import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.YVtils;

import java.io.File;
import java.io.IOException;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class DiscordConfigManager {
    public YamlConfiguration ConfigRequest() {
        File configfile = new File(YVtils.getInstance().getDataFolder(), "Discord/config.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public YamlConfiguration LinkedRequest() {
        File configfile = new File(YVtils.getInstance().getDataFolder(), "Discord/save.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public void LinkedWriter(String path, String value) {
        File configfile = new File(YVtils.getInstance().getDataFolder(), "Discord/save.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.set(path, value);
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
