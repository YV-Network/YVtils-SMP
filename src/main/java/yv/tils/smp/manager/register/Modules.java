package yv.tils.smp.manager.register;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.modules.discord.BotManager.BotStartStop;
import yv.tils.smp.modules.fun.ccr.CCRCommand;
import yv.tils.smp.modules.fun.ccr.InvListener;
import yv.tils.smp.modules.fun.sit.DismountListener;
import yv.tils.smp.modules.fun.sit.SitCommand;
import yv.tils.smp.modules.fun.sit.StairClickListener;
import yv.tils.smp.modules.status.JoinQuitStatus;
import yv.tils.smp.modules.status.StatusCommand;
import yv.tils.smp.modules.status.StatusCommandCompleter;
import yv.tils.smp.utils.configs.ConfigModeration;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class Modules {
    SMPPlugin main = SMPPlugin.getInstance();

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
            manager.registerEvents(new StairClickListener(), main);
        }

        //Timber
        //manager.registerEvents(new TimberListener(), main); - Durability Bug
        //VeinMiner
        //manager.registerEvents(new OreListener(), main); - Durability Bug
    }

}
