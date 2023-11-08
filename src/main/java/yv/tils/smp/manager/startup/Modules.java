package yv.tils.smp.manager.startup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.YVtils;
import yv.tils.smp.mods.discord.BotManager.BotStartStop;
import yv.tils.smp.mods.ccr.CCRCommand;
import yv.tils.smp.mods.ccr.InvListener;
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
    }

    public void registerStatusModule() {
        PluginManager manager = Bukkit.getPluginManager();

        main.getCommand("status").setTabCompleter(new StatusCommandCompleter());
        manager.registerEvents(new JoinQuitStatus(), main);
        main.getCommand("status").setExecutor(new StatusCommand());
    }

    public void registerFunModule() {
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
        }

        //Timber
        //manager.registerEvents(new TimberListener(), main); - Durability Bug
        //VeinMiner
        //manager.registerEvents(new OreListener(), main); - Durability Bug
    }

}
