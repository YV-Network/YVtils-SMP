package yv.tils.smp.utils.configs.discord;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.YVtils;
import yv.tils.smp.utils.configs.language.LanguageFile;

import java.io.File;
import java.io.IOException;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class config_yml {
    File file = new File(YVtils.getInstance().getDataFolder() + "/Discord", "config.yml");
    YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {
        ymlFile.addDefault("Active", true);
        ymlFile.addDefault("BotToken", LanguageFile.DirectFormatter("YOUR TOKEN HERE", "DEINEN BOT TOKEN"));
        ymlFile.addDefault("MainGuild", "Guild ID");

        ymlFile.addDefault("1#", LanguageFile.DirectFormatter("You can use Online; Idle; DND; Offline; Invisible", "Du kannst Online; Idle; DND; Offline; Invisible benutzen"));
        ymlFile.addDefault("2#", LanguageFile.DirectFormatter("You can use Watching; Playing; Competing; None", "Du kannst Watching; Playing; Competing; None benutzen"));
        ymlFile.addDefault("BotSettings.OnlineStatus", "online");
        ymlFile.addDefault("BotSettings.Activity", "none");
        ymlFile.addDefault("BotSettings.ActivityMessage", "Minecraft");

        ymlFile.addDefault("EmbedSettings.Author", "YVtils SMP");
        ymlFile.addDefault("EmbedSettings.AuthorIconURL", "URL");

        ymlFile.addDefault("WhitelistFeature.Channel", LanguageFile.DirectFormatter("CHANNEL ID", "KANAL ID"));
        ymlFile.addDefault("WhitelistFeature.Role", LanguageFile.DirectFormatter("ROLE ID 1, ROLE ID 2, ROLE ID ...", "ROLLEN ID 1, ROLLEN ID 2, ROLLEN ID ..."));
        ymlFile.addDefault("3#", LanguageFile.DirectFormatter("See here for Permission Names: https://ci.dv8tion.net/job/JDA5/javadoc/net/dv8tion/jda/api/Permission.html", "Siehe hier für die Namen der Berechtigungen: https://ci.dv8tion.net/job/JDA5/javadoc/net/dv8tion/jda/api/Permission.html"));
        ymlFile.addDefault("ServerInfoCommand.Permission", LanguageFile.DirectFormatter("PERMISSION", "BERECHTIGUNG"));

        ymlFile.addDefault("WhitelistCommand.Permission", LanguageFile.DirectFormatter("PERMISSION", "BERECHTIGUNG"));

        ymlFile.addDefault("ChatSync.Enabled", true);
        ymlFile.addDefault("ChatSync.Permission", LanguageFile.DirectFormatter("PERMISSION", "BERECHTIGUNG"));
        ymlFile.addDefault("ChatSync.Channel", LanguageFile.DirectFormatter("CHANNEL ID", "KANAL ID"));

        ymlFile.addDefault("ConsoleSync.Enabled", true);
        ymlFile.addDefault("ConsoleSync.Channel", LanguageFile.DirectFormatter("CHANNEL ID", "KANAL ID"));

        ymlFile.addDefault("LogChannel", LanguageFile.DirectFormatter("CHANNEL ID", "KANAL ID"));

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
