package yv.tils.smp.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.LanguageSystem.LanguageFile;
import yv.tils.smp.SMPPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 Creates the Config Files for the Plugin, and give them Input with Config Options
 * @since 4.6.6
 * @version 4.6.6
 */
public class ConfigModeration {

    /**
     Use all Config Methods!
     You can use as Config 'Langauge; MinecraftDiscordBridge; DoNotEdit; WhitelistedDiscordPlayers; StatusModule; StatusSave
     @since  4.6.6
     */
    public YamlConfiguration ConfigRequest(String config) {
        File configfile = new File(SMPPlugin.getInstance().getDataFolder(), config + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    /**
     Add values to the Config!
     You can use as Config 'Langauge; MinecraftDiscordBridge; DoNotEdit; WhitelistedDiscordPlayers; StatusModule; StatusSave
     @since  4.6.6
     */
    public YamlConfiguration ConfigContentAdd(String config, String path, String value) {
        File configfile = new File(SMPPlugin.getInstance().getDataFolder(), config + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        configuration.set(path, value);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    /**
     Add values to the Config!
     You can use as Config 'Langauge; MinecraftDiscordBridge; DoNotEdit; WhitelistedDiscordPlayers; StatusModule; StatusSave
     @since  4.6.6
     */
    public YamlConfiguration ConfigContentGet(String config, String path) {
        File configfile = new File(SMPPlugin.getInstance().getDataFolder(), config + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        configuration.get(path);
        return configuration;
    }

    /**
     Remove values from the Config!
     You can use as Config 'Langauge; MinecraftDiscordBridge; DoNotEdit; WhitelistedDiscordPlayers; StatusModule; StatusSave
     @since  4.6.6
     */
    public YamlConfiguration ConfigContentRemove(String config, String path) {
        File configfile = new File(SMPPlugin.getInstance().getDataFolder(), config + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        configuration.set(path, null);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    File mcdcbridgefile = new File(SMPPlugin.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration mcdcbridge = YamlConfiguration.loadConfiguration(mcdcbridgefile);
    File donoteditfile = new File(SMPPlugin.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration donotedit = YamlConfiguration.loadConfiguration(donoteditfile);
    File whitelisteddcplayersfile = new File(SMPPlugin.getInstance().getDataFolder(), "WhitelistedDiscordPlayers.yml");
    YamlConfiguration whitelisteddcplayers = YamlConfiguration.loadConfiguration(whitelisteddcplayersfile);
    File statusmodulefile = new File(SMPPlugin.getInstance().getDataFolder(), "StatusModule.yml");
    YamlConfiguration statusmodule = YamlConfiguration.loadConfiguration(statusmodulefile);
    File statussavefile = new File(SMPPlugin.getInstance().getDataFolder(), "StatusSave.yml");
    YamlConfiguration statussave = YamlConfiguration.loadConfiguration(statussavefile);

    public void onEntranceGeneration() {

        //MinecraftDiscordBridge.yml
        mcdcbridge.addDefault("Active", true);
        mcdcbridge.addDefault("BotToken", LanguageFile.DirectFormatter("YOUR TOKEN HERE","DEINEN BOT TOKEN"));
        mcdcbridge.addDefault("0#",  LanguageFile.DirectFormatter("You can insert here a URL (https://cdn.discordapp.com/attachments/887398222555930664/892066785766019112/buildattack.jpg) for a Icon in the Embeds!","Hier kannst du eine URL (https://cdn.discordapp.com/attachments/887398222555930664/892066785766019112/buildattack.jpg) einfügen, damit die Embeds ein Bild haben!"));
        mcdcbridge.addDefault("EmbedAuthorIcon", "");
        mcdcbridge.addDefault("1#", LanguageFile.DirectFormatter("You can use Streaming; Watching; Playing; Competing; None", "Du kannst Streaming; Watching; Playing; Competing; None benutzen"));
        mcdcbridge.addDefault("Activity", "none");
        mcdcbridge.addDefault("ActivityMessage", "Minecraft");
        mcdcbridge.addDefault("2#", LanguageFile.DirectFormatter("You only need a Url, when you use the Activity Streaming! Otherwise you don't need one","Du brauchst nur eine Url, wenn du die Aktivität Streaming ausgewählt hast! Ansonsten benötigst du keine"));
        mcdcbridge.addDefault("ActivityStreamingUrl", "");
        mcdcbridge.addDefault("3#", LanguageFile.DirectFormatter("You can use Online; Idle; DND; Offline; Invisible","Du kannst Online; Idle; DND; Offline; Invisible benutzen"));
        mcdcbridge.addDefault("OnlineStatus", "online");
        mcdcbridge.addDefault("4#", LanguageFile.DirectFormatter("Here you can set the Channel for the whitelist Feature! Players can write their MC Name in there and they will get whitelisted! Copy the Channel ID and paste it here", "Hier kannst du einen Kannal für das Whitelist Modul auswählen! Spieler können dann ihren MC Namen dort hineninschreiben und werden automatisch gewhitelistet! Kopiere die Kannal ID und füge sie hier ein"));
        mcdcbridge.addDefault("WhitelistChannelID", LanguageFile.DirectFormatter("CHANNEL ID HERE","KANAL ID HIER"));
        mcdcbridge.options().copyDefaults(true);

        //DoNotEdit.yml
        donotedit.addDefault("Started", false);
        donotedit.addDefault("MissingLanguage", false);
        donotedit.addDefault("MaintenanceMode", "false");
        donotedit.options().copyDefaults(true);

        //WhitelistedDiscordPlayers.yml
        whitelisteddcplayers.addDefault("DiscordName+Tag", "Minecraft Username + UUID");
        whitelisteddcplayers.options().copyDefaults(true);

        //StatusModule.yml
        statusmodule.addDefault("Active", true);
        statusmodule.addDefault("MaxStatusLength", 20);
        statusmodule.addDefault("0#",  LanguageFile.DirectFormatter("Here you can set the Default Status, which the Players can select with '/status default <status>'! Please use for Color Codes \"&\" and not \"§\"", "Hier kannst du die Voreingestellten Status einstellen, welche die Spieler mit '/status default <status>' auswählen können! Bitte benutze \"&\" und nicht \"§\" für Color Codes!"));
        statusmodule.addDefault("Default-Status", defaultstatuslist());
        statusmodule.options().copyDefaults(true);

        //StatusSave.yml
        statussave.addDefault("UUID", "STATUS");
        statussave.options().copyDefaults(true);

        onSave();
    }

    public void onSave() {
        try {
            mcdcbridge.save(mcdcbridgefile);
            donotedit.save(donoteditfile);
            whitelisteddcplayers.save(whitelisteddcplayersfile);
            statusmodule.save(statusmodulefile);
            statussave.save(statussavefile);
        } catch (IOException e) {
            System.out.println("-------");
            Bukkit.getConsoleSender().sendMessage("File creation Error! Please get Support on the YVtils Support Discord");
            System.out.println("-------");
        }
    }

    public void onNameGenerate() {
        try {
            onGenerate("MinecraftDiscordBridge.yml");
            onGenerate("DoNotEdit.yml");
            onGenerate("WhitelistedDiscordPlayers.yml");
            onGenerate("StatusModule.yml");
            onGenerate("StatusSave.yml");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGenerate(String name) throws Exception {
        File file = new File(SMPPlugin.getInstance().getDataFolder(), name);
        if (!file.exists()) {
                file.createNewFile();
        }}

    public List<String> defaultstatuslist() {
        List<String> list = new ArrayList<>();

        list.add("&7Status&e");
        list.add("&eStatus1&b");
        list.add("&5Status2&8");
        return list;
    }
}