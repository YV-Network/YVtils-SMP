package yv.tils.smp.manager.startup;

import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import yv.tils.smp.YVtils;
import yv.tils.smp.internalapi.Log;
import yv.tils.smp.internalapi.Runtime;
import yv.tils.smp.internalapi.Variables;
import yv.tils.smp.manager.commands.Maintenance;
import yv.tils.smp.utils.configs.ConfigModeration;
import yv.tils.smp.utils.configs.other.FileGenerator;
import yv.tils.smp.utils.updater.VersionGetter;

/**
 * @since 4.6.8
 * @version CH2-1.0.0
 */
public class Other {
    YVtils main = YVtils.getInstance();

    public void registerUpdateChecker() {
        new Runtime().loadedMods();
        new VersionGetter().updateChecker(Variables.PluginVersion);
    }

    public void registerConfigs() throws Exception {
        main.saveDefaultConfig();

        FileGenerator fileGenerator = new FileGenerator();
        fileGenerator.generateFiles();

        //Discord Module
        new Log("DiscordModule Config");
        yv.tils.smp.utils.configs.discord.config_yml discordModuleConfig = new yv.tils.smp.utils.configs.discord.config_yml();
        discordModuleConfig.StringInput();
        yv.tils.smp.utils.configs.discord.save_yml linkedAccountsConfig = new yv.tils.smp.utils.configs.discord.save_yml();
        linkedAccountsConfig.StringInput();

        //Status Module
        new Log("StatusModule Config");
        yv.tils.smp.utils.configs.status.config_yml statusModuleConfig = new yv.tils.smp.utils.configs.status.config_yml();
        statusModuleConfig.StringInput();
        yv.tils.smp.utils.configs.status.save_yml statusConfig = new yv.tils.smp.utils.configs.status.save_yml();
        statusConfig.StringInput();

        //CCR Module
        new Log("CCRModule Config");
        yv.tils.smp.utils.configs.ccr.config_yml ccrModuleConfig = new yv.tils.smp.utils.configs.ccr.config_yml();
        ccrModuleConfig.StringInput();
        yv.tils.smp.utils.configs.ccr.save_yml ccrConfig = new yv.tils.smp.utils.configs.ccr.save_yml();
        ccrConfig.StringInput();

        if (new ConfigModeration().ConfigRequest("DoNotEdit").getString("MaintenanceMode").equals("true")) {
            Maintenance.maintenance = true;
        }

        new Log("Configs loaded");
    }

    public void RegisterOther() {

    }

    public void registerbStats() {
        Metrics metrics = new Metrics(YVtils.getInstance(), 14257);
        metrics.addCustomChart(new SimplePie("language", () -> YVtils.getInstance().getConfig().getString("Language")));
        metrics.addCustomChart(new SimplePie("discord", () -> YVtils.getInstance().getConfig().getString("Modules.Discord")));
        metrics.addCustomChart(new SimplePie("status", () -> YVtils.getInstance().getConfig().getString("Modules.Status")));
        metrics.addCustomChart(new SimplePie("sit", () -> YVtils.getInstance().getConfig().getString("Modules.Sit")));
        metrics.addCustomChart(new SimplePie("ccr", () -> YVtils.getInstance().getConfig().getString("Modules.CCR")));
    }
}

