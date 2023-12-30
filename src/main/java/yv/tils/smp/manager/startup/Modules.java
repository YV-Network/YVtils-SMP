package yv.tils.smp.manager.startup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.YVtils;
import yv.tils.smp.mods.ccr.CCRCommand;
import yv.tils.smp.mods.ccr.InvListener;
import yv.tils.smp.mods.discord.BotManager.BotStartStop;
import yv.tils.smp.mods.discord.sync.ChatSync;
import yv.tils.smp.mods.discord.whitelist.ImportWhitelist;
import yv.tils.smp.mods.sit.DismountListener;
import yv.tils.smp.mods.sit.SitCommand;
import yv.tils.smp.mods.status.JoinQuitStatus;
import yv.tils.smp.mods.status.StatusCommand;
import yv.tils.smp.mods.status.StatusCommandCompleter;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class Modules {
    YVtils main = YVtils.getInstance();

    PluginManager manager = Bukkit.getPluginManager();

    BotStartStop botStartStop = new BotStartStop();

    public void registerDiscordModule() {
        botStartStop.TokenCheck();
        new ImportWhitelist().Importer();
        manager.registerEvents(new ChatSync(), main);
    }

    public void registerStatusModule() {
        main.getCommand("status").setTabCompleter(new StatusCommandCompleter());
        manager.registerEvents(new JoinQuitStatus(), main);
        main.getCommand("status").setExecutor(new StatusCommand());
    }

    public void registerSitModule() {
        main.getCommand("sit").setExecutor(new SitCommand());
        manager.registerEvents(new DismountListener(), main);
    }

    public void registerCCRModule() {
        main.getCommand("ccr").setExecutor(new CCRCommand());
        manager.registerEvents(new InvListener(), main);
    }
}
