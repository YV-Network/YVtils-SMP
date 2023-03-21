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
    File file = new File(SMPPlugin.getInstance().getDataFolder() + "/discord", "config.yml");
    YamlConfiguration ymlfile = YamlConfiguration.loadConfiguration(file);

/*
    public void StringInput() {
        ymlfile.addDefault("Active", true);
        ymlfile.addDefault("BotToken", LanguageFile.DirectFormatter("YOUR TOKEN HERE","DEINEN BOT TOKEN"));
        ymlfile.addDefault("0#",  LanguageFile.DirectFormatter("You can insert here a URL (https://cdn.discordapp.com/attachments/887398222555930664/892066785766019112/buildattack.jpg) for a Icon in the Embeds!","Hier kannst du eine URL (https://cdn.discordapp.com/attachments/887398222555930664/892066785766019112/buildattack.jpg) einfügen, damit die Embeds ein Bild haben!"));
        ymlfile.addDefault("EmbedAuthorIcon", "");
        ymlfile.addDefault("1#", LanguageFile.DirectFormatter("You can use Streaming; Watching; Playing; Competing; None", "Du kannst Streaming; Watching; Playing; Competing; None benutzen"));
        ymlfile.addDefault("Activity", "none");
        ymlfile.addDefault("ActivityMessage", "Minecraft");
        ymlfile.addDefault("2#", LanguageFile.DirectFormatter("You only need a Url, when you use the Activity Streaming! Otherwise you don't need one","Du brauchst nur eine Url, wenn du die Aktivität Streaming ausgewählt hast! Ansonsten benötigst du keine"));
        ymlfile.addDefault("ActivityStreamingUrl", "");
        ymlfile.addDefault("3#", LanguageFile.DirectFormatter("You can use Online; Idle; DND; Offline; Invisible","Du kannst Online; Idle; DND; Offline; Invisible benutzen"));
        ymlfile.addDefault("OnlineStatus", "online");
        ymlfile.addDefault("4#", LanguageFile.DirectFormatter("Here you can set the Channel for the whitelist Feature! Players can write their MC Name in there and they will get whitelisted! Copy the Channel ID and paste it here", "Hier kannst du einen Kannal für das Whitelist Modul auswählen! Spieler können dann ihren MC Namen dort hineninschreiben und werden automatisch gewhitelistet! Kopiere die Kannal ID und füge sie hier ein"));
        ymlfile.addDefault("WhitelistChannelID", LanguageFile.DirectFormatter("CHANNEL ID HERE","KANAL ID HIER"));
        ymlfile.addDefault("LogChannel", LanguageFile.DirectFormatter("CHANNEL ID HERE","KANAL ID HIER"));
        ymlfile.options().copyDefaults(true);
        fileSave();
    }
 */

    public void StringInput() {
        ymlfile.addDefault("Active", true);
        ymlfile.addDefault("BotToken", LanguageFile.DirectFormatter("YOUR TOKEN HERE","DEINEN BOT TOKEN"));
        ymlfile.addDefault("MainGuild", "Guild ID");

        ymlfile.addDefault("1#", LanguageFile.DirectFormatter("You can use Online; Idle; DND; Offline; Invisible","Du kannst Online; Idle; DND; Offline; Invisible benutzen"));
        ymlfile.addDefault("BotSettings.OnlineStatus", "online");
        ymlfile.addDefault("2#", LanguageFile.DirectFormatter("You can use Watching; Playing; Competing; None", "Du kannst Watching; Playing; Competing; None benutzen"));
        ymlfile.addDefault("BotSettings.Activity", "none");
        ymlfile.addDefault("BotSettings.ActivityMessage", "Minecraft");

        ymlfile.addDefault("EmbedSettings.Author", "YVtils SMP");
        ymlfile.addDefault("EmbedSettings.AuthorIconURL", "URL");

        ymlfile.addDefault("WhitelistFeature.Channel", LanguageFile.DirectFormatter("CHANNEL ID","KANAL ID"));
        ymlfile.addDefault("WhitelistFeature.Role", LanguageFile.DirectFormatter("ROLE ID","ROLLEN ID"));

        ymlfile.addDefault("HelpCommand.Permission.DiscordHelpCMD", LanguageFile.DirectFormatter("PERMISSION","BERECHTIGUNG"));
        ymlfile.addDefault("HelpCommand.Permission.IngameHelpCMD", LanguageFile.DirectFormatter("PERMISSION","BERECHTIGUNG"));

        ymlfile.addDefault("WhitelistCommand.Permission", LanguageFile.DirectFormatter("PERMISSION","BERECHTIGUNG"));

        ymlfile.addDefault("ChatSync.Enabled", true);
        ymlfile.addDefault("ChatSync.Permission", LanguageFile.DirectFormatter("PERMISSION","BERECHTIGUNG"));
        ymlfile.addDefault("ChatSync.Channel", LanguageFile.DirectFormatter("CHANNEL ID","KANAL ID"));

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
