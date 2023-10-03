package yv.tils.smp.utils.configs.discord;

import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.SMPPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class DiscordConfigManager {
    public YamlConfiguration ConfigRequest() {
        File configfile = new File(SMPPlugin.getInstance().getDataFolder(), "Discord/config.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public YamlConfiguration LinkedRequest() {
        File configfile = new File(SMPPlugin.getInstance().getDataFolder(), "Discord/linkedAccounts.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public void LinkedWriter(String path,String value) {
        File configfile = new File(SMPPlugin.getInstance().getDataFolder(), "Discord/linkedAccounts.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.set(path, value);
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public YamlConfiguration StatsRequest() {
        File configfile = new File(SMPPlugin.getInstance().getDataFolder(), "Discord/stats.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public void StatsWriter(String path, String value) {
        File configfile = new File(SMPPlugin.getInstance().getDataFolder(), "Discord/stats.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.set(path, value);
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
