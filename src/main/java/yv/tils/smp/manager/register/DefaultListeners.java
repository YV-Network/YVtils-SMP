package yv.tils.smp.manager.register;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.commands.FlyCommand;
import yv.tils.smp.commands.GlobalMuteCommand;
import yv.tils.smp.commands.GodCommand;
import yv.tils.smp.commands.VanishCommand;
import yv.tils.smp.listeners.*;
import yv.tils.smp.logger.logger.*;
import yv.tils.smp.modules.adminstration.invsee.GUIEdit;
import yv.tils.smp.modules.discord.sync.ChatSync;
import yv.tils.smp.utils.ConfigVersionUpdateChecker;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class DefaultListeners {
    SMPPlugin main = SMPPlugin.getInstance();

    GlobalMuteCommand globalMuteCommand = new GlobalMuteCommand();
    FlyCommand flyCommand = new FlyCommand();
    VanishCommand vanishCommand = new VanishCommand();
    GodCommand godCommand = new GodCommand();

    public void registerListener() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(globalMuteCommand, main);
        manager.registerEvents(flyCommand, main);
        manager.registerEvents(vanishCommand, main);
        manager.registerEvents(godCommand, main);
        manager.registerEvents(new JoinListener(), main);
        manager.registerEvents(new QuitListener(), main);
        manager.registerEvents(new MotdListener(), main);
        manager.registerEvents(new SpawnBoostListener(main), main);
        manager.registerEvents(new ConfigVersionUpdateChecker(), main);
        manager.registerEvents(new ChatListener(), main);
        manager.registerEvents(new GUIEdit(), main);

        manager.registerEvents(new ChatSync(), main);
    }

    public void registerLogger() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new BlockInteract(), main);
        manager.registerEvents(new PlayerServerEvent(), main);
        manager.registerEvents(new ChatEvent(), main);
        manager.registerEvents(new ChestInteract(), main);
        manager.registerEvents(new EntityKillEvent(), main);
        manager.registerEvents(new ItemInteract(), main);
    }
}
