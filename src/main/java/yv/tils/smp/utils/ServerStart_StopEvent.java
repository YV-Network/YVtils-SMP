package yv.tils.smp.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import yv.tils.smp.*;
import yv.tils.smp.commands.*;
import yv.tils.smp.commands.autocompleter.*;
import yv.tils.smp.commands.replacecommands.*;
import yv.tils.smp.eventlogger.logger.*;
import yv.tils.smp.listeners.*;
import yv.tils.smp.logger.*;
import yv.tils.smp.modules.discord.*;
import yv.tils.smp.modules.fun.ccr.*;
import yv.tils.smp.modules.fun.sit.DismountListener;
import yv.tils.smp.modules.fun.sit.SitCommand;
import yv.tils.smp.modules.fun.sit.SitManager;
import yv.tils.smp.modules.fun.sit.StairClickListener;
import yv.tils.smp.modules.status.*;
import yv.tils.smp.placeholder.*;
import yv.tils.smp.updateutils.*;
import yv.tils.smp.updateutils.database.*;
import yv.tils.smp.utils.language.CreateFile_de;
import yv.tils.smp.utils.language.CreateFile_en;
import yv.tils.smp.utils.language.LanguageFile;
import yv.tils.smp.utils.language.LanguageMessage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.7
 */
public class ServerStart_StopEvent {

    SMPPlugin main = SMPPlugin.getInstance();

    public static final SitManager sitManager = new SitManager();

    File file1 = new File(SMPPlugin.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration modifyFile1 = YamlConfiguration.loadConfiguration(file1);

    GlobalMuteCommand globalMuteCommand = new GlobalMuteCommand();
    FlyCommand flyCommand = new FlyCommand();
    VanishCommand vanishCommand = new VanishCommand();
    GodCommand godCommand = new GodCommand();
    BotStartStop botStartStop = new BotStartStop();

    public void RegisterAll() {
        new ConsoleLog("Configs - Loading");
        registerConfigs();
        new ConsoleLog("Configs - Loaded -- OtherThings - Loading");
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
        if (modifyFile1.getBoolean("Active")) registerDiscordModule();
        new ConsoleLog("DiscordModule - Loaded -- StatusModule - Loading");
        if (new ConfigModeration().ConfigRequest("StatusModule").getBoolean("Active")) registerStatusModule();
        new ConsoleLog("StatusModule - Loaded -- FunModule - Loading");
        if (new ConfigModeration().ConfigRequest("FunModule").getBoolean("Active")) registerFunModule();
        new ConsoleLog("FunModule - Loaded -- UpdateChecker - Loading");
        registerUpdateChecker();
        new ConsoleLog("UpdateChecker - Loaded -- / Logger - Loading");
        //registerLogger();
        new ConsoleLog("Logger - Loaded -- / x - Loading");
    }

    private void RegisterOther() {

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
        main.getCommand("flywalkspeed").setTabCompleter(new FlyWalkSpeedAutoCompleter());
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

    private void registerDiscordModule() {
        botStartStop.TokenCheck();
    }

    private void registerStatusModule() {
        PluginManager manager = Bukkit.getPluginManager();

        main.getCommand("status").setTabCompleter(new StatusCommandCompleter());
        manager.registerEvents(new JoinQuitStatus(), main);
        main.getCommand("status").setExecutor(new StatusCommand());
    }

    private void registerFunModule() {
        PluginManager manager = Bukkit.getPluginManager();

        //CustomCraftingRecipes (CCR)
        if (new ConfigModeration().ConfigRequest("FunModule").getBoolean("CCR.General")) {
            main.getCommand("ccr").setExecutor(new CCRCommand());
            manager.registerEvents(new InvListener(), main);
        }

        //Sit
        if (new ConfigModeration().ConfigRequest("FunModule").getBoolean("Sit.General")) {
            main.getCommand("sit").setExecutor(new SitCommand());
            manager.registerEvents(new DismountListener(), main);
            manager.registerEvents(new StairClickListener(), main);
        }

        //Timber
        //manager.registerEvents(new TimberListener(), main); - Durability Bug
        //VeinMiner
        //manager.registerEvents(new OreListener(), main); - Durability Bug
    }

    private void registerLogger() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new BlockInteract(), main);
        manager.registerEvents(new PlayerServerEvent(), main);
        manager.registerEvents(new ChatEvent(), main);
        manager.registerEvents(new ChestInteract(), main);
        manager.registerEvents(new EntityKillEvent(), main);
        manager.registerEvents(new ItemInteract(), main);
    }

    private void registerConfigs() {
        main.saveDefaultConfig();
        ConfigModeration configModeration = new ConfigModeration();
        configModeration.onNameGenerate();
        configModeration.onEntranceGeneration();
        if (main.getConfig().getBoolean("StartupAnnouncement") && AnnouncementPlaceholder.STARTUPANNOUNCE() != null) {
            Bukkit.getConsoleSender().sendMessage(AnnouncementPlaceholder.STARTUPANNOUNCE());
        }

        CreateFile_de createFile_de = new CreateFile_de();
        createFile_de.StringInput();
        CreateFile_en createFile_en = new CreateFile_en();
        createFile_en.StringInput();
        LanguageFile.LanguageFileGet();

        if (new ConfigModeration().ConfigRequest("DoNotEdit").getString("MaintenanceMode").equals("true")) {
            main.maintenances = true;
        }
    }

    private void registerUpdateChecker() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinAnnouncer(), main);
        if (new VersionChecker().VersionChecker_FullRelease(new Variables().PluginVersion).equals("UA")) {
            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("PREFIXUPDATE");
            list2.add(MessagePlaceholder.PREFIXUPDATE);
            list1.add("NEWVERSION");
            list2.add(new VersionChecker().NewestVersion());
            list1.add("OLDVERSION");
            list2.add(new Variables().PluginVersion);
            list1.add("LINK");
            list2.add("https://modrinth.com/plugin/yvtils_smp");

            Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLUGIN_UPDATE_AVAILABLE), list1, list2));
        }else {
            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("PREFIXNOUPDATE");
            list2.add(MessagePlaceholder.PREFIXNOUPDATE);

            Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLUGIN_UP_TO_DATE), list1, list2));
        }
    }

    //

    public void UnregisterAll() {
        unregisterOther();
        if (modifyFile1.getBoolean("Active")) {
            unregisterDiscord();
        }}

    private void unregisterOther() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            new JoinQuitStatus().PlayerStatusSave(player);
        }

        new ConfigModeration().onSave();
        new CreateFile_de().fileSave();
        new CreateFile_en().fileSave();
    }

    private void unregisterDiscord() {
        botStartStop.onStop();
    }
}