package yv.tils.smp.utils.configs.language;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.YVtils;
import yv.tils.smp.utils.internalapi.Log;

import java.io.File;

/**
 * @since 4.6.6
 * @version 4.6.7
 */
public class LanguageFile {

    private static YamlConfiguration yamlConfiguration;

    public static void LanguageFileGet() {
        File file = new File(YVtils.getInstance().getDataFolder() + "/Language", YVtils.getInstance().getConfig().getString("Language") + ".yml");
        if (file.exists()) {
            yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        }else {
            new Log(file.getPath());
            Bukkit.getConsoleSender().sendMessage(DirectFormatter("In the config is an unavailable language file given!", "In der Config wurde ein nicht verfügbares Sprachen File angegeben."));
        }
    }

    /**
     * @Use: getMessage(LangaugeMessage.message);
     * @since 4.6.6
     * @version 4.6.6
     */
    public static String getMessage(LanguageMessage message) {
        return yamlConfiguration.getString(message.name().toUpperCase());
    }

    /**
     * Set Messages in German/English without the Language Config File
     * @When_to_use? This Method can be used, when a Message shouldn't be configurable over the language File!
     * @since 4.6.6
     * @version 4.6.6
     */
    public static String DirectFormatter(String en, String de) {
        String s;
        if (YVtils.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (YVtils.getInstance().getConfig().getString("Language").equals("de")) {
            s = de;
        }else {
            s = en;
        }
        return s;
    }
}
