package yv.tils.smp.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.placeholder.AnnouncementPlaceholder;
import yv.tils.smp.placeholder.LanguagePlaceholder;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.commands.*;
import yv.tils.smp.commands.autocompleter.FlySpeedAutoCompleter;
import yv.tils.smp.commands.autocompleter.GamemodeAutoCompleter;
import yv.tils.smp.commands.autocompleter.ModerationAutoCompleter;
import yv.tils.smp.commands.autocompleter.VanishAutoCompleter;
import yv.tils.smp.commands.replacecommands.*;
import yv.tils.smp.discord.BotStartStop;
import yv.tils.smp.listeners.*;
import yv.tils.smp.otherfiles.CustomCraftingRecipes;

import java.io.File;

public class ServerStart_StopEvent {

    SMPPlugin main = SMPPlugin.getInstance();

    File file1 = new File(SMPPlugin.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration modifyFile1 = YamlConfiguration.loadConfiguration(file1);

    DamageKickCommand damageKickCommand = new DamageKickCommand();
    ChatMuteCommand chatMuteCommand = new ChatMuteCommand();
    FlyCommand flyCommand = new FlyCommand();
    VanishCommand vanishCommand = new VanishCommand();
    GodCommand godCommand = new GodCommand();
    BotStartStop botStartStop = new BotStartStop();

    public void RegisterAll() {
        RegisterOther();
        registerCommands();
        registerListener();
        registerTabCompleter();
        registerCommandReplace();
        if (modifyFile1.getBoolean("Active")) {
            registerDiscord();
        }
    }

    private void RegisterOther() {
        main.saveDefaultConfig();
        ConfigModeration configModeration = new ConfigModeration();
        configModeration.onNameGenerate();
        configModeration.onEntranceGeneration();
        if (main.getConfig().getBoolean("StartupAnnouncement")) {
            Bukkit.getConsoleSender().sendMessage(AnnouncementPlaceholder.STARTUPANNOUNCE());
        }
        if (main.getConfig().getBoolean("CustomRecipes")) {
            new CustomCraftingRecipes().addToCraftingManager();
        }
        new UpdateChecker(main, 97642).getLatestVersion(version -> {
            if(main.getDescription().getVersion().equalsIgnoreCase(version)) {
                Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.UpToDate());
            } else {
                Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.UpdateAvailable());
            }
        });
    }

    private void registerCommands() {
        main.getCommand("chatmute").setExecutor(chatMuteCommand);
        main.getCommand("afkdamage").setExecutor(damageKickCommand);
        main.getCommand("fly").setExecutor(flyCommand);
        main.getCommand("vanish").setExecutor(vanishCommand);
        main.getCommand("flywalkspeed").setExecutor(new FlyWalkSpeed());
        main.getCommand("start").setExecutor(new StartCommand());
        main.getCommand("moderation").setExecutor(new ModerationCommand());
        main.getCommand("gm").setExecutor(new GamemodeCommand());
        main.getCommand("directmessage").setExecutor(new MessageCommand());
        main.getCommand("reply").setExecutor(new ReplyCommand());
        main.getCommand("god").setExecutor(godCommand);
        main.getCommand("heal").setExecutor(new HealCommand());
    }

    private void registerTabCompleter() {
        main.getCommand("moderation").setTabCompleter(new ModerationAutoCompleter());
        main.getCommand("flywalkspeed").setTabCompleter(new FlySpeedAutoCompleter());
        main.getCommand("gm").setTabCompleter(new GamemodeAutoCompleter());
        main.getCommand("vanish").setTabCompleter(new VanishAutoCompleter());
    }

    private void registerCommandReplace() {
        PluginManager manager = Bukkit.getPluginManager();
        if (main.getConfig().getBoolean("CommandBlock./seed")) {
            manager.registerEvents(new seedcommand(), main);
        }
        if (main.getConfig().getBoolean("CommandBlock./pardon")) {
            manager.registerEvents(new pardonCommand(), main);
        }
        if (main.getConfig().getBoolean("CommandBlock./msg")) {
            manager.registerEvents(new msgCommand(), main);
        }
        if (main.getConfig().getBoolean("CommandBlock./kick")) {
            manager.registerEvents(new KickCommand(), main);
        }
        if (main.getConfig().getBoolean("CommandBlock./ban")) {
            manager.registerEvents(new BanCommand(), main);
        }
    }

    private void registerListener() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(chatMuteCommand, main);
        manager.registerEvents(damageKickCommand, main);
        manager.registerEvents(flyCommand, main);
        manager.registerEvents(vanishCommand, main);
        manager.registerEvents(godCommand, main);
        manager.registerEvents(new JoinListener(), main);
        manager.registerEvents(new QuitListener(), main);
        manager.registerEvents(new MotdListener(), main);
        manager.registerEvents(new SpawnBoostListener(main), main);
        manager.registerEvents(new ConfigVersionUpdateChecker(), main);
        manager.registerEvents(new ChatListener(), main);
    }

    private void registerDiscord() {
        botStartStop.TokenCheck();
    }

    //

    public void UnregisterAll() {
        unregisterOther();
        if (modifyFile1.getBoolean("Active")) {
            unregisterDiscord();
        }
    }

    private void unregisterOther() {
        new ConfigModeration().onSave();
    }

    private void unregisterDiscord() {
        botStartStop.onStop();
    }
}
