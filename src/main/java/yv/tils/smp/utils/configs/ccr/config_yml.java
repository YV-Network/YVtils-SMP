package yv.tils.smp.utils.configs.ccr;

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
    File file = new File(YVtils.getInstance().getDataFolder() + "/CCR", "config.yml");
    YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {
        ymlFile.addDefault("Crafting.LightBlock", "true");
        ymlFile.addDefault("Crafting.InvisItemFrame", "true");
        ymlFile.addDefault("Crafting.GlowingNetheriteElytra", "true");
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