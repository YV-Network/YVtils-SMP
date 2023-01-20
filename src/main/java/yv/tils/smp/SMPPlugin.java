package yv.tils.smp;

import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import yv.tils.smp.modules.fun.sit.SitManager;
import yv.tils.smp.utils.language.LanguageFile;
import yv.tils.smp.utils.language.LanguageMessage;
import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.ServerStart_StopEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @since 1.0
 * @version 4.6.7
 */
public final class SMPPlugin extends JavaPlugin {

    private static SMPPlugin instance;
    public final List<UUID> fly = new ArrayList<>();
    public final List<UUID> fly1 = new ArrayList<>();
    public List<UUID> vanished = new ArrayList();
    public List<UUID> godmode = new ArrayList();
    public List<UUID> godmode1 = new ArrayList();
    public List<UUID> InvClose = new ArrayList<>();
    private HashMap<UUID, UUID> recentMessages;
    public JDA jda;
    public boolean maintenances;
    public boolean globalmute;
    public boolean database_connection;

    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        new ConsoleLog(LanguageFile.DirectFormatter("YVtils-SMP begun loading!", "YVtils-SMP beginnt zu laden!"));
        new ServerStart_StopEvent().RegisterAll();

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
        new ServerStart_StopEvent().UnregisterAll();
        Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.STOP_COMPLETED_MESSAGE), list1, list2));
    }

    public static SMPPlugin getInstance() {
        return instance;
    }
    public HashMap<UUID, UUID> getRecentMessages() {
        return recentMessages;
    }

}