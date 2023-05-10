package yv.tils.smp;

import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.manager.register.Summarizer;
import yv.tils.smp.manager.unregister.Other;
import yv.tils.smp.modules.discord.Whitelist.ImportWhitelist;
import yv.tils.smp.modules.fun.sit.SitManager;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.configs.discord.DiscordConfigManager;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @since 1.0
 * @version 4.6.8
 */
public final class SMPPlugin extends JavaPlugin {

    private static SMPPlugin instance;
    public static final SitManager sitManager = new SitManager();
    public final List<UUID> fly = new ArrayList<>();
    public final List<UUID> fly1 = new ArrayList<>();
    public List<UUID> vanished = new ArrayList();
    public List<UUID> godmode = new ArrayList();
    public List<UUID> godmode1 = new ArrayList();
    public List<UUID> InvClose = new ArrayList<>();

    public List<String> serveripID = new ArrayList<>();
    public List<String> serverversionID = new ArrayList<>();
    public List<String> playercountID = new ArrayList<>();

    public final List<String> WhitelistManager = new ArrayList<>();
    private HashMap<UUID, UUID> recentMessages;
    public boolean maintenances;
    public boolean globalmute;
    public boolean database_connection;

    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        new ConsoleLog(LanguageFile.DirectFormatter("YVtils-SMP begun loading!", "YVtils-SMP beginnt zu laden!"));

        Metrics metrics = new Metrics(this, 14257);
        metrics.addCustomChart(new SimplePie("language", () -> getConfig().getString("Language")));

        new Summarizer().RegisterAll();

        new ImportWhitelist().Importer();

        List<String> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        list1.add("PREFIXENABLE");
        list2.add(MessagePlaceholder.PREFIXENABLE);

        new ConsoleLog("ServerStartStopEvent - Loaded -- (Recent)Messages - Loading");
        Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.START_MESSAGE), list1, list2));
        recentMessages = new HashMap<>();
        Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.START_COMPLETED_MESSAGE), list1, list2));
        new ConsoleLog("Everything- Loaded");
    }

    @Override
    public void onDisable() {
        List<String> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        list1.add("PREFIXDISABLE");
        list2.add(MessagePlaceholder.PREFIXDISABLE);

        Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.STOP_MESSAGE), list1, list2));
        new Other().unregisterOther();
        if (new DiscordConfigManager().ConfigRequest().getBoolean("Active")) {
            new Other().unregisterDiscord();
        }
        Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.STOP_COMPLETED_MESSAGE), list1, list2));
    }

    public static SMPPlugin getInstance() {
        return instance;
    }
    public HashMap<UUID, UUID> getRecentMessages() {
        return recentMessages;
    }

}
