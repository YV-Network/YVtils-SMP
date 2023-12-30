package yv.tils.smp.manager.startup;

import yv.tils.smp.YVtils;
import yv.tils.smp.utils.internalapi.Log;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class Summarizer {
    public void onEnable() throws Exception {
        new Log("Configs - Loading");
        new Other().registerConfigs();
        new Log("OtherThings - Loading");
        new Other().RegisterOther();
        new Log("Commands - Loading");
        new DefaultCommands().registerCommands();
        new Log("TabCompleter - Loading");
        new DefaultCommands().registerTabCompleter();
        new Log("CommandReplace - Loading");
        new DefaultCommands().registerCommandReplace();
        new Log("Listener - Loading");
        new DefaultListeners().registerListener();
        new Log("Mods - Loading");
        new Log("DiscordModule - Loading");
        if (YVtils.getInstance().getConfig().getBoolean("Modules.Discord")) new Modules().registerDiscordModule();
        new Log("StatusModule - Loading");
        if (YVtils.getInstance().getConfig().getBoolean("Modules.Status")) new Modules().registerStatusModule();
        new Log("SitModule - Loading");
        if (YVtils.getInstance().getConfig().getBoolean("Modules.Sit")) new Modules().registerSitModule();
        new Log("CCRModule - Loading");
        if (YVtils.getInstance().getConfig().getBoolean("Modules.CCR")) new Modules().registerCCRModule();
        new Log("UpdateChecker - Loading");
        if (YVtils.getInstance().getConfig().getBoolean("UpdateCheck")) new Other().registerUpdateChecker();
        new Log("Logger - Loading");
        new DefaultListeners().registerLogger();
        new Log("bStats - Loading");
        new Other().registerBStats();
    }
}
