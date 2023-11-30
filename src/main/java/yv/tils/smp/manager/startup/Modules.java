package yv.tils.smp.manager.startup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.YVtils;
import yv.tils.smp.mods.discord.BotManager.BotStartStop;
import yv.tils.smp.mods.ccr.CCRCommand;
import yv.tils.smp.mods.ccr.InvListener;
import yv.tils.smp.mods.discord.whitelist.ImportWhitelist;
import yv.tils.smp.mods.sit.DismountListener;
import yv.tils.smp.mods.sit.SitCommand;
import yv.tils.smp.mods.status.JoinQuitStatus;
import yv.tils.smp.mods.status.StatusCommand;
import yv.tils.smp.mods.status.StatusCommandCompleter;
import yv.tils.smp.utils.configs.ConfigModeration;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class Modules {
    YVtils main = YVtils.getInstance();

    BotStartStop botStartStop = new BotStartStop();

    public void registerDiscordModule() {
        botStartStop.TokenCheck();
        new ImportWhitelist().Importer();
    }

    public void registerStatusModule() {
        PluginManager manager = Bukkit.getPluginManager();

        main.getCommand("status").setTabCompleter(new StatusCommandCompleter());
        manager.registerEvents(new JoinQuitStatus(), main);
        main.getCommand("status").setExecutor(new StatusCommand());
    }

    public void registerSitModule() {
        PluginManager manager = Bukkit.getPluginManager();

        main.getCommand("sit").setExecutor(new SitCommand());
        manager.registerEvents(new DismountListener(), main);
    }

    public void registerCCRModule() {
        PluginManager manager = Bukkit.getPluginManager();

        main.getCommand("ccr").setExecutor(new CCRCommand());
        manager.registerEvents(new InvListener(), main);
    }
}
