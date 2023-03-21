package yv.tils.smp.manager.register;

import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.utils.configs.ConfigModeration;
import yv.tils.smp.utils.configs.discord.DiscordConfigManager;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class Summarizer {

    public void RegisterAll() {
        new ConsoleLog("Configs - Loading");
        new Other().registerConfigs();
        new ConsoleLog("Configs - Loaded -- OtherThings - Loading");
        new Other().RegisterOther();
        new ConsoleLog("OtherThings - Loaded -- Commands - Loading");
        new DefaultCommands().registerCommands();
        new ConsoleLog("Commands - Loaded -- TabCompleter - Loading");
        new DefaultCommands().registerTabCompleter();
        new ConsoleLog("TabCompleter - Loaded -- CommandReplace - Loading");
        new DefaultCommands().registerCommandReplace();
        new ConsoleLog("CommandReplace - Loaded -- Listener - Loading");
        new DefaultListeners().registerListener();
        new ConsoleLog("Listener - Loaded -- DiscordModule - Loading");
        if (new DiscordConfigManager().ConfigRequest().getBoolean("Active")) new Modules().registerDiscordModule();
        new ConsoleLog("DiscordModule - Loaded -- StatusModule - Loading");
        if (new ConfigModeration().ConfigRequest("StatusModule").getBoolean("Active")) new Modules().registerStatusModule();
        new ConsoleLog("StatusModule - Loaded -- FunModule - Loading");
        if (new ConfigModeration().ConfigRequest("FunModule").getBoolean("Active")) new Modules().registerFunModule();
        new ConsoleLog("FunModule - Loaded -- UpdateChecker - Loading");
        new Other().registerUpdateChecker();
        new ConsoleLog("UpdateChecker - Loaded -- / Logger - Loading");
        new DefaultListeners().registerLogger();
        new ConsoleLog("Logger - Loaded -- / x - Loading");
    }

}
