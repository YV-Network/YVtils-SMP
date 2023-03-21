package yv.tils.smp.manager.register;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.Variables;
import yv.tils.smp.logger.logger.*;
import yv.tils.smp.placeholder.AnnouncementPlaceholder;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.updateutils.JoinAnnouncer;
import yv.tils.smp.updateutils.database.VersionChecker;
import yv.tils.smp.utils.configs.ConfigModeration;
import yv.tils.smp.utils.configs.discord.DiscordModuleConfig;
import yv.tils.smp.utils.configs.discord.LinkedAccountsConfig;
import yv.tils.smp.utils.configs.language.CreateFile_de;
import yv.tils.smp.utils.configs.language.CreateFile_en;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class Other {
    SMPPlugin main = SMPPlugin.getInstance();

    public void registerUpdateChecker() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinAnnouncer(), main);
        if (new VersionChecker().VersionChecker_FullRelease(new Variables().PluginVersion).equals("UA")) {
            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("PREFIXUPDATE");
            list2.add(MessagePlaceholder.PREFIXUPDATE);
            list1.add("NEWVERSION");
            list2.add(new VersionChecker().NewestVersion());
            list1.add("OLDVERSION");
            list2.add(new Variables().PluginVersion);
            list1.add("LINK");
            list2.add("https://modrinth.com/plugin/yvtils_smp");

            Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLUGIN_UPDATE_AVAILABLE), list1, list2));
        } else if (new VersionChecker().VersionChecker_FullRelease(new Variables().PluginVersion).equals("ERROR")) {
            Bukkit.getConsoleSender().sendMessage(LanguageFile.DirectFormatter("The Update Checker has an error! Please contact the Support, if you want to fix this.", "Beim checken nach einem Update ist ein Fehler aufgetreten! Bitte kontaktiere den Support!"));
        } else {
            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("PREFIXNOUPDATE");
            list2.add(MessagePlaceholder.PREFIXNOUPDATE);

            Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLUGIN_UP_TO_DATE), list1, list2));
        }
    }

    public void registerConfigs() {
        main.saveDefaultConfig();
        ConfigModeration configModeration = new ConfigModeration();
        configModeration.onNameGenerate();
        configModeration.onEntranceGeneration();
        if (main.getConfig().getBoolean("StartupAnnouncement") && AnnouncementPlaceholder.STARTUPANNOUNCE() != null) {
            Bukkit.getConsoleSender().sendMessage(AnnouncementPlaceholder.STARTUPANNOUNCE());
        }

        //Language System
        CreateFile_de createFile_de = new CreateFile_de();
        createFile_de.StringInput();
        CreateFile_en createFile_en = new CreateFile_en();
        createFile_en.StringInput();
        LanguageFile.LanguageFileGet();

        //Discord Module
        DiscordModuleConfig discordModuleConfig = new DiscordModuleConfig();
        discordModuleConfig.StringInput();
        LinkedAccountsConfig linkedAccountsConfig = new LinkedAccountsConfig();
        linkedAccountsConfig.StringInput();

        if (new ConfigModeration().ConfigRequest("DoNotEdit").getString("MaintenanceMode").equals("true")) {
            main.maintenances = true;
        }
    }

    public void RegisterOther() {

    }
}

