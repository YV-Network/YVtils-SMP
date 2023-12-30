package yv.tils.smp.utils.configs.status;

import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.YVtils;

import java.io.File;
import java.io.IOException;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class StatusConfigManager {
    public YamlConfiguration ConfigRequest() {
        File configfile = new File(YVtils.getInstance().getDataFolder(), "Status/config.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public YamlConfiguration SavedRequest() {
        File configfile = new File(YVtils.getInstance().getDataFolder(), "Status/save.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public void SavedWriter(String path, String value) {
        File configfile = new File(YVtils.getInstance().getDataFolder(), "Status/save.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.set(path, value);
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}