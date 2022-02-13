package yv.tils.smp.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.Placeholder.LanguagePlaceholder;
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

        System.out.println(SMPPlugin.getInstance().getDataFolder());

        //Language.yml
        if (Objects.equals(SMPPlugin.getInstance().getConfig().getString("Language"), "en")) {
            //English
            modifyFile.addDefault("#", "Use & for Color Codes.");
            modifyFile.addDefault("Already_Started_Message", "&d<Project Name> already started!");
        }else if (Objects.equals(SMPPlugin.getInstance().getConfig().getString("Language"), "de")) {
            //Deutsch
            modifyFile.addDefault("#", "Benutze & für Color Codes.");
            modifyFile.addDefault("Already_Started_Message", "&d<Projekt Name> wurde bereits gestartet!");
        }else {
            modifyFile2.set("MissingLanguage", true);
            onSave();
            modifyFile.addDefault("Bug" , LanguagePlaceholder.ConfigCreateMissingLanguage());
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.PREFIXERROR + " " + LanguagePlaceholder.ConfigCreateMissingLanguage());
        }
        modifyFile.options().copyDefaults(true);

        //MinecraftDiscordBridge.yml
        modifyFile1.addDefault("Active", true);
        modifyFile1.addDefault("BotToken", LanguagePlaceholder.ConfigCreateBotToken());
        modifyFile1.options().copyDefaults(true);

        //DoNotEdit.yml
        modifyFile2.addDefault("Started", false);
        modifyFile2.addDefault("MissingLanguage", false);
        modifyFile2.options().copyDefaults(true);

        onSave();
    }

    public void onSave() {
        try {
            modifyFile.save(file);
        } catch (IOException e) {
            System.out.println("---1---");
            Bukkit.getConsoleSender().sendMessage("Error 1");
            System.out.println("---1---");
        }
        try {
            modifyFile1.save(file1);
        } catch (IOException e) {
            System.out.println("---2---");
            Bukkit.getConsoleSender().sendMessage("Error 2");
            System.out.println("---2---");
        }
        try {
            modifyFile2.save(file2);
        } catch (IOException e) {
            System.out.println("---3---");
            Bukkit.getConsoleSender().sendMessage("Error 3");
            System.out.println("---3---");
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