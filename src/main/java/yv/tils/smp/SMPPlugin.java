package yv.tils.smp;

import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import yv.tils.smp.placeholder.LanguagePlaceholder;
import yv.tils.smp.utils.ServerStart_StopEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class SMPPlugin extends JavaPlugin {

    private static SMPPlugin instance;
    public final List<UUID> fly = new ArrayList<>();
    public final List<UUID> fly1 = new ArrayList<>();
    public List<UUID> vanished = new ArrayList();
    public List<UUID> godmode = new ArrayList();
    public List<UUID> godmode1 = new ArrayList();
    private HashMap<UUID, UUID> recentMessages;
    public JDA jda;

    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.StartMessage());
        new ServerStart_StopEvent().RegisterAll();
        recentMessages = new HashMap<>();
        Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.StartCompletedMessage());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.StopMessage());
        new ServerStart_StopEvent().UnregisterAll();
        Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.StopCompletedMessage());
    }

    public static SMPPlugin getInstance() {
        return instance;
    }
    public HashMap<UUID, UUID> getRecentMessages() {
        return recentMessages;
    }
}