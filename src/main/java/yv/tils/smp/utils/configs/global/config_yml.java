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

    //# You can find a full documentation for this file here: https://yvnetwork.de/yvtils/smp

    File file = new File(YVtils.getInstance().getDataFolder(), "config.yml");
    YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {
        ymlFile.addDefault("", "You can find a full documentation for this file here: https://yvnetwork.de/yvtils/smp");
        ymlFile.addDefault("Language", "en");
        ymlFile.addDefault("UpdateCheck", true);
        ymlFile.addDefault("Modules.Discord", true);
        ymlFile.addDefault("Modules.Status", true);
        ymlFile.addDefault("Modules.Sit", true);
        ymlFile.addDefault("Modules.MultiMine", true);
        ymlFile.addDefault("Modules.CCR", true);
        ymlFile.addDefault("Modules.OldVersion", true);
        ymlFile.addDefault("Modules.Server", true);
        ymlFile.addDefault("Modules.Admin", true);
        ymlFile.addDefault("Debug", false);

        ymlFile.options().copyDefaults(true);
        fileSave();
    }

    public void fileSave() {
        try {
            ymlFile.save(file);
        } catch (IOException e) {
            System.out.println("-------");
            Bukkit.getConsoleSender().sendMessage("File creation Error! Please get Support on the YVtils Support Discord");
            System.out.println("-------");
        }
    }

}