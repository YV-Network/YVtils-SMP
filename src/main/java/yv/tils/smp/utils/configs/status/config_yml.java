package yv.tils.smp.utils.configs.status;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.YVtils;
import yv.tils.smp.utils.configs.language.LanguageFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class config_yml {
    File file = new File(YVtils.getInstance().getDataFolder() + "/Status", "config.yml");
    YamlConfiguration ymlfile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {

        ymlfile.addDefault("Active", true);
        ymlfile.addDefault("MaxStatusLength", 20);
        ymlfile.addDefault("0#", LanguageFile.DirectFormatter("Here you can set the Default Status, which the Players can select with '/status default <status>'! Please use for Color Codes \"&\" and not \"§\"", "Hier kannst du die Voreingestellten Status einstellen, welche die Spieler mit '/status default <status>' auswählen können! Bitte benutze \"&\" und nicht \"§\" für Color Codes!"));
        ymlfile.addDefault("Default-Status", defaultstatuslist());
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

    private List<String> defaultstatuslist() {
        List<String> list = new ArrayList<>();

        list.add("&7Status&e");
        list.add("&eStatus1&b");
        list.add("&5Status2&8");
        return list;
    }
}