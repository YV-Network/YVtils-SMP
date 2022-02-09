package yv.tils.smp.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.Placeholder.MessagePlaceholder;
import yv.tils.smp.SMPPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ConfigModeration {

    File file = new File(SMPPlugin.getInstance().getDataFolder(), "Language.yml");
    YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(file);
    File file1 = new File(SMPPlugin.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration modifyFile1 = YamlConfiguration.loadConfiguration(file1);
    File file2 = new File(SMPPlugin.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration modifyFile2 = YamlConfiguration.loadConfiguration(file2);

    public void onEntranceGeneration() {

        //Language.yml
        if (Objects.equals(SMPPlugin.getInstance().getConfig().getString("Language"), "en")) {
            //English
            modifyFile.addDefault("Command", "Use & for Color Codes.");
            modifyFile.addDefault("Already_Started_Message", "&d<Project Name> already started!");
        }else if (Objects.equals(SMPPlugin.getInstance().getConfig().getString("Language"), "de")) {
            //Deutsch
            modifyFile.addDefault("Command", "Benutze & für Color Codes.");
            modifyFile.addDefault("Already_Started_Message", "&d<Projekt Name> wurde bereits gestartet!");
        }else {
            modifyFile2.set("MissingLanguage", true);
            onSave();
            modifyFile.addDefault("Bug" , "This language is not available in the Moment! Help to translate: " + "https://discord.com/invite/y6uJYzdHc5");
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXERROR + " §7This language is not available in the Moment! Help to translate: " + "https://discord.com/invite/y6uJYzdHc5");
        }

        //MinecraftDiscordBridge.yml
        modifyFile1.addDefault("Active", true);
        modifyFile1.addDefault("BotToken", "YOUR TOKEN HERE");

        //DoNotEdit.yml
        modifyFile2.addDefault("Started", false);
        modifyFile2.addDefault("MissingLanguage", false);

        onSave();
    }

    public void onSave() {
        try {
            modifyFile.save(file);
            modifyFile1.save(file1);
            modifyFile2.save(file2);
        } catch (IOException e) {
            System.out.println("-------");
            e.printStackTrace();
            System.out.println("-------");
        }
    }

    public void onNameGenerate() {
        try {
            onGenerate("Language.yml");
            onGenerate("MinecraftDiscordBridge.yml");
            onGenerate("DoNotEdit.yml");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGenerate(String name) throws Exception {
        File file = new File(SMPPlugin.getInstance().getDataFolder(), name);
        if (!file.exists()) {
                file.createNewFile();
        }}}