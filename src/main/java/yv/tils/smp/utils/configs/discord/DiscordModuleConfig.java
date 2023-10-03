package yv.tils.smp.utils.configs.discord;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.utils.configs.language.LanguageFile;

import java.io.File;
import java.io.IOException;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class DiscordModuleConfig {
    File file = new File(SMPPlugin.getInstance().getDataFolder() + "/Discord", "config.yml");
    YamlConfiguration ymlfile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {
        ymlfile.addDefault("Active", true);
        ymlfile.addDefault("BotToken", LanguageFile.DirectFormatter("YOUR TOKEN HERE","DEINEN BOT TOKEN"));
        ymlfile.addDefault("MainGuild", "Guild ID");

        ymlfile.addDefault("1#", LanguageFile.DirectFormatter("You can use Online; Idle; DND; Offline; Invisible","Du kannst Online; Idle; DND; Offline; Invisible benutzen"));
        ymlfile.addDefault("2#", LanguageFile.DirectFormatter("You can use Watching; Playing; Competing; None", "Du kannst Watching; Playing; Competing; None benutzen"));
        ymlfile.addDefault("BotSettings.OnlineStatus", "online");
        ymlfile.addDefault("BotSettings.Activity", "none");
        ymlfile.addDefault("BotSettings.ActivityMessage", "Minecraft");

        ymlfile.addDefault("EmbedSettings.Author", "YVtils SMP");
        ymlfile.addDefault("EmbedSettings.AuthorIconURL", "URL");

        ymlfile.addDefault("WhitelistFeature.Channel", LanguageFile.DirectFormatter("CHANNEL ID","KANAL ID"));
        ymlfile.addDefault("WhitelistFeature.Role", LanguageFile.DirectFormatter("ROLE ID 1, ROLE ID 2, ROLE ID ...","ROLLEN ID 1, ROLLEN ID 2, ROLLEN ID ..."));

        //ymlfile.addDefault("HelpCommand.Permission.DiscordHelpCMD", LanguageFile.DirectFormatter("PERMISSION","BERECHTIGUNG"));
        //ymlfile.addDefault("HelpCommand.Permission.IngameHelpCMD", LanguageFile.DirectFormatter("PERMISSION","BERECHTIGUNG"));

        ymlfile.addDefault("3#", LanguageFile.DirectFormatter("See here for Permission Names: https://ci.dv8tion.net/job/JDA5/javadoc/net/dv8tion/jda/api/Permission.html", "Siehe hier für die Namen der Berechtigungen: https://ci.dv8tion.net/job/JDA5/javadoc/net/dv8tion/jda/api/Permission.html"));
        ymlfile.addDefault("ServerInfoCommand.Permission", LanguageFile.DirectFormatter("PERMISSION","BERECHTIGUNG"));

        ymlfile.addDefault("WhitelistCommand.Permission", LanguageFile.DirectFormatter("PERMISSION","BERECHTIGUNG"));

        ymlfile.addDefault("ChatSync.Enabled", true);
        ymlfile.addDefault("ChatSync.Permission", LanguageFile.DirectFormatter("PERMISSION","BERECHTIGUNG"));
        ymlfile.addDefault("ChatSync.Channel", LanguageFile.DirectFormatter("CHANNEL ID","KANAL ID"));

        ymlfile.addDefault("ConsoleSync.Enabled", true);
        ymlfile.addDefault("ConsoleSync.Channel", LanguageFile.DirectFormatter("CHANNEL ID","KANAL ID"));

        ymlfile.addDefault("LogChannel", LanguageFile.DirectFormatter("CHANNEL ID","KANAL ID"));

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
