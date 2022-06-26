package yv.tils.smp.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.LanguageSystem.LanguageFile;
import yv.tils.smp.LanguageSystem.LanguageMessage;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.commands.*;
import yv.tils.smp.commands.autocompleter.*;
import yv.tils.smp.commands.replacecommands.*;
import yv.tils.smp.listeners.*;
import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.modules.discord.BotStartStop;
import yv.tils.smp.modules.status.JoinQuitStatus;
import yv.tils.smp.modules.status.StatusCommand;
import yv.tils.smp.modules.status.StatusCommandCompleter;
import yv.tils.smp.placeholder.AnnouncementPlaceholder;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.placeholder.StringReplacer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class ServerStart_StopEvent {

    SMPPlugin main = SMPPlugin.getInstance();

    File file1 = new File(SMPPlugin.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration modifyFile1 = YamlConfiguration.loadConfiguration(file1);

    GlobalMuteCommand globalMuteCommand = new GlobalMuteCommand();
    FlyCommand flyCommand = new FlyCommand();
    VanishCommand vanishCommand = new VanishCommand();
    GodCommand godCommand = new GodCommand();
    BotStartStop botStartStop = new BotStartStop();

    public void RegisterAll() {
        new ConsoleLog("OtherThings - Loading");
        RegisterOther();
        new ConsoleLog("OtherThings - Loaded -- Commands - Loading");
        registerCommands();
        new ConsoleLog("Commands - Loaded -- Listener - Loading");
        registerListener();
        new ConsoleLog("Listener - Loaded -- TabCompleter - Loading");
        registerTabCompleter();
        new ConsoleLog("TabCompleter - Loaded -- CommandReplace - Loading");
        registerCommandReplace();
        new ConsoleLog("CommandReplace - Loaded -- DiscordModule - Loading");
        if (modifyFile1.getBoolean("Active")) registerDiscord();
        new ConsoleLog("DiscordModule - Loaded -- StatusModule - Loading");
        if (new ConfigModeration().ConfigRequest("StatusModule").getBoolean("Active")) registerStatusModule();
        new ConsoleLog("StatusModule - Loaded");
    }

    private void RegisterOther() {
        main.saveDefaultConfig();
        ConfigModeration configModeration = new ConfigModeration();
        configModeration.onNameGenerate();
        configModeration.onEntranceGeneration();
        if (main.getConfig().getBoolean("StartupAnnouncement") && AnnouncementPlaceholder.STARTUPANNOUNCE() != null) {
            Bukkit.getConsoleSender().sendMessage(AnnouncementPlaceholder.STARTUPANNOUNCE());
        }
        if (main.getConfig().getBoolean("CustomRecipes")) {
            //new CustomCraftingRecipes().addToCraftingManager();
        }
        new UpdateChecker(main,97642).getLatestVersion(version -> {
            if(main.getDescription().getVersion().equalsIgnoreCase(version)) {

                List<String> list1 = new ArrayList();
                List<String> list2 = new ArrayList();
                list1.add("PREFIXNOUPDATE");
                list2.add(MessagePlaceholder.PREFIXNOUPDATE);

                Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLUGIN_UP_TO_DATE), list1, list2));
            } else {

                List<String> list1 = new ArrayList();
                List<String> list2 = new ArrayList();
                list1.add("PREFIXUPDATE");
                list2.add(MessagePlaceholder.PREFIXUPDATE);
                list1.add("LINK");
                list2.add("https://www.spigotmc.org/resources/yvtils-smp.97642/history");

                Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLUGIN_UPDATE_AVAILABLE), list1, list2));
            }
        });

        main.saveResource("Language/" + "en.yml", false);
        main.saveResource("Language/" + "de.yml", false);
        LanguageFile.LanguageFileGet();

        if (new ConfigModeration().ConfigRequest("DoNotEdit").getString("MaintenanceMode").equals("true")) {
            main.maintenances = true;
        }
    }

    private void registerCommands() {
        main.getCommand("chatmute").setExecutor(globalMuteCommand);
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
        main.getCommand("maintenance").setExecutor(new MaintenanceCommand());
    }

    private void registerTabCompleter() {
        main.getCommand("moderation").setTabCompleter(new ModerationAutoCompleter());
        main.getCommand("flywalkspeed").setTabCompleter(new FlySpeedAutoCompleter());
        main.getCommand("gm").setTabCompleter(new GamemodeAutoCompleter());
        main.getCommand("vanish").setTabCompleter(new VanishAutoCompleter());
        main.getCommand("maintenance").setTabCompleter(new MainteanceAutoCompleter());
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
        manager.registerEvents(globalMuteCommand, main);
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

    private void registerStatusModule() {
        PluginManager manager = Bukkit.getPluginManager();

        main.getCommand("status").setTabCompleter(new StatusCommandCompleter());
        manager.registerEvents(new JoinQuitStatus(), main);
        main.getCommand("status").setExecutor(new StatusCommand());
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
