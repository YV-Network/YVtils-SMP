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
    YamlConfiguration ymlfile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {
        ymlfile.addDefault("Crafting.LightBlock", "true");
        ymlfile.addDefault("Crafting.InvisItemFrame", "true");
        ymlfile.addDefault("Crafting.GlowingNetheriteElytra", "true");
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