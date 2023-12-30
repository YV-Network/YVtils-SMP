package yv.tils.smp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import yv.tils.smp.manager.shutdown.Other;
import yv.tils.smp.manager.startup.LanguageFiles;
import yv.tils.smp.manager.startup.Summarizer;
import yv.tils.smp.mods.sit.SitManager;
import yv.tils.smp.placeholder.Prefix;
import yv.tils.smp.utils.configs.discord.DiscordConfigManager;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.internalapi.Log;
import yv.tils.smp.utils.internalapi.StringReplacer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @version CH2-1.0.0
 * @since 1.0
 */
public final class YVtils extends JavaPlugin {
    public static final SitManager sitManager = new SitManager();
    private static YVtils instance;
    public final List<String> whitelistManager = new ArrayList<>();
    public List<UUID> invClose = new ArrayList<>();
    public boolean chatSyncID = true;
    private HashMap<UUID, UUID> recentMessages;

    public static YVtils getInstance() {
        return instance;
    }

    public void onLoad() {
        instance = this;
    }

    public HashMap<UUID, UUID> getRecentMessages() {
        return recentMessages;
    }


    @Override
    public void onEnable() {
        new Log(LanguageFile.DirectFormatter("YVtils-SMP is now loading!", "YVtils-SMP beginnt zu laden!"));
        new LanguageFiles().onEnable();

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("PREFIXENABLE");
        list2.add(Prefix.PREFIXENABLE);

        Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.START_MESSAGE), list1, list2));

        try {
            new Summarizer().onEnable();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //getPluginLoader().disablePlugin(this);
            Bukkit.getPluginManager().disablePlugin(this);
        }

        recentMessages = new HashMap<>();
        Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.START_COMPLETED_MESSAGE), list1, list2));
        new Log("Everything- Loaded");
    }

    @Override
    public void onDisable() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("PREFIXDISABLE");
        list2.add(Prefix.PREFIXDISABLE);

        Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.STOP_MESSAGE), list1, list2));
        new Other().unregisterOther();
        if (new DiscordConfigManager().ConfigRequest().getBoolean("Active")) {
            new Other().unregisterDiscord();
        }
        Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.STOP_COMPLETED_MESSAGE), list1, list2));
    }
}
