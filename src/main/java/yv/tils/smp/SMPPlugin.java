package yv.tils.smp;

import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import yv.tils.smp.LanguageSystem.LanguageFile;
import yv.tils.smp.LanguageSystem.LanguageMessage;
import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.utils.ServerStart_StopEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @since 1.0
 * @version 4.6.6
 */
public final class SMPPlugin extends JavaPlugin {

    private static SMPPlugin instance;
    public final List<UUID> fly = new ArrayList<>();
    public final List<UUID> fly1 = new ArrayList<>();
    public List<UUID> vanished = new ArrayList();
    public List<UUID> godmode = new ArrayList();
    public List<UUID> godmode1 = new ArrayList();
    private HashMap<UUID, UUID> recentMessages;
    public JDA jda;
    public boolean maintenances;

    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        new ConsoleLog(LanguageFile.DirectFormatter("YVtils-SMP begun loading!", "YVtils-SMP beginnt zu laden!"));
        new ServerStart_StopEvent().RegisterAll();
        Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.START_MESSAGE));
        recentMessages = new HashMap<>();
        Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.START_COMPLETED_MESSAGE));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.STOP_MESSAGE));
        new ServerStart_StopEvent().UnregisterAll();
        Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.STOP_COMPLETED_MESSAGE));
    }

    public static SMPPlugin getInstance() {
        return instance;
    }
    public HashMap<UUID, UUID> getRecentMessages() {
        return recentMessages;
    }
}