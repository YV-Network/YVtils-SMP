package yv.tils.smp.utils.configs.global;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.YVtils;

import java.io.File;
import java.io.IOException;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class config_yml {

    //# You can find a documentation for this file here: https://yvnetwork.de/yvtils/smp

    File file = new File(YVtils.getInstance().getDataFolder(), "config.yml");
    YamlConfiguration ymlfile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {
        ymlfile.addDefault("", "You can find a full documentation for this file here: https://yvnetwork.de/yvtils/smp");
        ymlfile.addDefault("Language", "en");
        ymlfile.addDefault("UpdateCheck", true);
        ymlfile.addDefault("Modules.Discord", true);
        ymlfile.addDefault("Modules.Status", true);
        ymlfile.addDefault("Modules.Sit", true);
        ymlfile.addDefault("Modules.MultiMine", true);
        ymlfile.addDefault("Modules.CCR", true);
        ymlfile.addDefault("Modules.OldVersion", true);
        ymlfile.addDefault("Modules.Server", true);
        ymlfile.addDefault("Modules.Admin", true);
        ymlfile.addDefault("Debug", false);

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