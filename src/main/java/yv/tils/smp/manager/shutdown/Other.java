package yv.tils.smp.manager.shutdown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import yv.tils.smp.mods.ccr.InvListener;
import yv.tils.smp.mods.discord.BotManager.BotStartStop;
import yv.tils.smp.mods.status.JoinQuitStatus;
import yv.tils.smp.utils.configs.ConfigModeration;
import yv.tils.smp.utils.configs.language.CreateFile_de;
import yv.tils.smp.utils.configs.language.CreateFile_en;

/**
 * @since 4.6.8
 * @version CH2-1.0.0
 */
public class Other {

    BotStartStop botStartStop = new BotStartStop();

    public void unregisterOther() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            new JoinQuitStatus().PlayerStatusSave(player);
            new InvListener().onInvClose(player.getOpenInventory().getTopInventory(), player);
            player.closeInventory();
        }

        new ConfigModeration().onSave();
        new CreateFile_de().fileSave();
        new CreateFile_en().fileSave();
        new yv.tils.smp.utils.configs.global.config_yml().fileSave();
        new yv.tils.smp.utils.configs.ccr.config_yml().fileSave();
        new yv.tils.smp.utils.configs.ccr.save_yml().fileSave();
        new yv.tils.smp.utils.configs.discord.config_yml().fileSave();
        new yv.tils.smp.utils.configs.discord.save_yml().fileSave();
        new yv.tils.smp.utils.configs.status.config_yml().fileSave();
        new yv.tils.smp.utils.configs.status.save_yml().fileSave();
    }

    public void unregisterDiscord() {
        botStartStop.onStop();
    }
}
