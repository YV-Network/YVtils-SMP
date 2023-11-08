package yv.tils.smp.utils.configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.YVtils;
import yv.tils.smp.utils.configs.language.LanguageFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 Creates the Config Files for the Plugin, and give them Input with Config Options
 * @since 4.6.6
 * @version 4.6.8
 * @deprecated
 */
public class ConfigModeration {

    /**
     Use all Config Methods!
     You can use as Config 'Langauge; MinecraftDiscordBridge; DoNotEdit; WhitelistedDiscordPlayers; StatusModule; StatusSave
     @since  4.6.6
     */
    public YamlConfiguration ConfigRequest(String config) {
        File configfile = new File(YVtils.getInstance().getDataFolder(), config + ".yml");
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
     You can use as Config 'Language; MinecraftDiscordBridge; DoNotEdit; WhitelistedDiscordPlayers; StatusModule; StatusSave
     @since  4.6.6
     */
    public YamlConfiguration ConfigContentAdd(String config, String path, String value) {
        File configfile = new File(YVtils.getInstance().getDataFolder(), config + ".yml");
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
     Get values out of the Config!
     You can use as Config 'Langauge; MinecraftDiscordBridge; DoNotEdit; WhitelistedDiscordPlayers; StatusModule; StatusSave
     @since  4.6.6
     */
    public Object ConfigContentGet(String config, String path) {
        File configfile = new File(YVtils.getInstance().getDataFolder(), config + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);

        return configuration.get(path);
    }

    /**
     Remove values from the Config!
     You can use as Config 'Langauge; MinecraftDiscordBridge; DoNotEdit; WhitelistedDiscordPlayers; StatusModule; StatusSave
     @since  4.6.6
     */
    public YamlConfiguration ConfigContentRemove(String config, String path) {
        File configfile = new File(YVtils.getInstance().getDataFolder(), config + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        configuration.set(path, null);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    File donoteditfile = new File(YVtils.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration donotedit = YamlConfiguration.loadConfiguration(donoteditfile);
    File funmodulefile = new File(YVtils.getInstance().getDataFolder(), "FunModule.yml");
    YamlConfiguration funmodule = YamlConfiguration.loadConfiguration(funmodulefile);

    public void onEntranceGeneration() {
        //DoNotEdit.yml
        donotedit.addDefault("Started", false);
        donotedit.addDefault("MissingLanguage", false);
        donotedit.addDefault("MaintenanceMode", "false");
        donotedit.options().copyDefaults(true);

        //FunModule
        funmodule.addDefault("Active", true);
        funmodule.addDefault("CCR.General", true);
        funmodule.addDefault("CCR.InvisibleItemFrame", true);
        funmodule.addDefault("CCR.LightBlock", true);
        funmodule.addDefault("CCR.GlowingNetheriteElytra", true);
        funmodule.addDefault("Sit.General", true);
        funmodule.options().copyDefaults(true);

        onSave();
    }

    public void onSave() {
        try {
            donotedit.save(donoteditfile);
            funmodule.save(funmodulefile);
        } catch (IOException e) {
            System.out.println("-------");
            Bukkit.getConsoleSender().sendMessage("File creation Error! Please get Support on the YVtils Support Discord");
            System.out.println("-------");
        }
    }

    public void onNameGenerate() {
        try {
            onFolderGenerate("Language");
            onFolderGenerate("Discord");

            onGenerate("DoNotEdit.yml", false);
            onGenerate("StatusModule.yml", true);
            onGenerate("StatusSave.yml", true);
            onGenerate("FunModule.yml", true);

            onGenerate("Discord/config.yml", true);
            onGenerate("Discord/linkedAccounts.yml", true);

            onGenerate("Language/de.yml", true);
            onGenerate("Language/en.yml", true);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFolderGenerate(String folder) {
        File dataFolder = new File(YVtils.getInstance().getDataFolder() + "/" + folder);
        if(!dataFolder.exists())
        {
            dataFolder.mkdir();
        }
    }

    public void onGenerate(String name, boolean editable) throws Exception {
        File file = new File(YVtils.getInstance().getDataFolder(), name);
        if (!file.exists()) {
            file.createNewFile();
        }}


}