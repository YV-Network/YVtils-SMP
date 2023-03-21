package yv.tils.smp.manager.unregister;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.commands.FlyCommand;
import yv.tils.smp.commands.GlobalMuteCommand;
import yv.tils.smp.commands.GodCommand;
import yv.tils.smp.commands.VanishCommand;
import yv.tils.smp.modules.discord.BotManager.BotStartStop;
import yv.tils.smp.modules.status.JoinQuitStatus;
import yv.tils.smp.utils.configs.ConfigModeration;
import yv.tils.smp.utils.configs.language.CreateFile_de;
import yv.tils.smp.utils.configs.language.CreateFile_en;

import java.io.File;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class Other {
    SMPPlugin main = SMPPlugin.getInstance();

    File file1 = new File(SMPPlugin.getInstance().getDataFolder(), "Discord/config.yml");
    YamlConfiguration modifyFile1 = YamlConfiguration.loadConfiguration(file1);

    GlobalMuteCommand globalMuteCommand = new GlobalMuteCommand();
    FlyCommand flyCommand = new FlyCommand();
    VanishCommand vanishCommand = new VanishCommand();
    GodCommand godCommand = new GodCommand();
    BotStartStop botStartStop = new BotStartStop();

    public void unregisterOther() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            new JoinQuitStatus().PlayerStatusSave(player);
        }

        new ConfigModeration().onSave();
        new CreateFile_de().fileSave();
        new CreateFile_en().fileSave();
    }

    public void unregisterDiscord() {
        botStartStop.onStop();
    }
}
