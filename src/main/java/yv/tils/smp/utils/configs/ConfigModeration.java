package yv.tils.smp.utils.configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.YVtils;
import yv.tils.smp.utils.configs.language.LanguageFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 Creates the Config Files for the Plugin, and give them Input with Config Options
 * @since 4.6.6
 * @version 4.6.8
 * @deprecated
 */
public class ConfigModeration {

    /**
     Use all Config Methods!
     You can use as Config 'Langauge; MinecraftDiscordBridge; DoNotEdit; WhitelistedDiscordPlayers; StatusModule; StatusSave
     @since  4.6.6
     */
    public YamlConfiguration ConfigRequest(String config) {
        File configfile = new File(YVtils.getInstance().getDataFolder(), config + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    File donoteditfile = new File(YVtils.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration donotedit = YamlConfiguration.loadConfiguration(donoteditfile);

    public void onSave() {
        try {
            donotedit.save(donoteditfile);
        } catch (IOException e) {
            System.out.println("-------");
            Bukkit.getConsoleSender().sendMessage("File creation Error! Please get Support on the YVtils Support Discord");
            System.out.println("-------");
        }
    }

}