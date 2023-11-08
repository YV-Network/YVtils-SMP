package yv.tils.smp.manager.startup;

import yv.tils.smp.YVtils;
import yv.tils.smp.internalapi.Runtime;
import yv.tils.smp.internalapi.Variables;
import yv.tils.smp.utils.configs.ConfigModeration;
import yv.tils.smp.utils.configs.discord.config_yml;
import yv.tils.smp.utils.configs.discord.save_yml;
import yv.tils.smp.utils.configs.language.CreateFile_de;
import yv.tils.smp.utils.configs.language.CreateFile_en;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.updater.VersionGetter;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class Other {
    YVtils main = YVtils.getInstance();

    public void registerUpdateChecker() {
        new Runtime().loadedMods();
        new VersionGetter().updateChecker(Variables.PluginVersion);
    }

    public void registerConfigs() {
        main.saveDefaultConfig();
        ConfigModeration configModeration = new ConfigModeration();
        configModeration.onNameGenerate();
        configModeration.onEntranceGeneration();

        //Language System
        CreateFile_de createFile_de = new CreateFile_de();
        createFile_de.StringInput();
        CreateFile_en createFile_en = new CreateFile_en();
        createFile_en.StringInput();
        LanguageFile.LanguageFileGet();

        //Discord Module
        config_yml discordModuleConfig = new config_yml();
        discordModuleConfig.StringInput();
        save_yml linkedAccountsConfig = new save_yml();
        linkedAccountsConfig.StringInput();

        if (new ConfigModeration().ConfigRequest("DoNotEdit").getString("MaintenanceMode").equals("true")) {
            main.maintenances = true;
        }
    }

    public void RegisterOther() {

    }
}

