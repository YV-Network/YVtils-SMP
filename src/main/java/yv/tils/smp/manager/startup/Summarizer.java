package yv.tils.smp.manager.startup;

import yv.tils.smp.internalapi.Log;
import yv.tils.smp.utils.configs.ConfigModeration;
import yv.tils.smp.utils.configs.discord.DiscordConfigManager;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class Summarizer {

    public void RegisterAll() {
        new Log("Configs - Loading");
        new Other().registerConfigs();
        new Log("Configs - Loaded -- OtherThings - Loading");
        new Other().RegisterOther();
        new Log("OtherThings - Loaded -- Commands - Loading");
        new DefaultCommands().registerCommands();
        new Log("Commands - Loaded -- TabCompleter - Loading");
        new DefaultCommands().registerTabCompleter();
        new Log("TabCompleter - Loaded -- CommandReplace - Loading");
        new DefaultCommands().registerCommandReplace();
        new Log("CommandReplace - Loaded -- Listener - Loading");
        new DefaultListeners().registerListener();
        new Log("Listener - Loaded -- DiscordModule - Loading");
        if (new DiscordConfigManager().ConfigRequest().getBoolean("Active")) new Modules().registerDiscordModule();
        new Log("DiscordModule - Loaded -- StatusModule - Loading");
        if (new ConfigModeration().ConfigRequest("StatusModule").getBoolean("Active")) new Modules().registerStatusModule();
        new Log("StatusModule - Loaded -- FunModule - Loading");
        if (new ConfigModeration().ConfigRequest("FunModule").getBoolean("Active")) new Modules().registerFunModule();
        new Log("FunModule - Loaded -- UpdateChecker - Loading");
        new Other().registerUpdateChecker();
        new Log("UpdateChecker - Loaded -- / Logger - Loading");
        new DefaultListeners().registerLogger();
        new Log("Logger - Loaded -- / x - Loading");
    }

}
