package yv.tils.smp.manager.startup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.YVtils;
import yv.tils.smp.commands.FlyCommand;
import yv.tils.smp.commands.GlobalMuteCommand;
import yv.tils.smp.commands.GodCommand;
import yv.tils.smp.commands.VanishCommand;
import yv.tils.smp.manager.listener.*;
import yv.tils.smp.mods.admin.logger.logger.*;
import yv.tils.smp.mods.admin.invsee.GUIEdit;
import yv.tils.smp.mods.other.SpawnElytra;

/**
 * @since 4.6.8
 * @version CH2-1.0.0
 */
public class DefaultListeners {
    YVtils main = YVtils.getInstance();

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
        manager.registerEvents(new GUIEdit(), main);

/*
                    |           |
                    | Chapter 2 |
                    v           v
*/

        manager.registerEvents(new ChatListener(), main);
        manager.registerEvents(new LoginListener(), main);
        manager.registerEvents(new ServerListPingListener(), main);
        manager.registerEvents(new AirTimeListener(), main);
        manager.registerEvents(new DamageListener(), main);
        manager.registerEvents(new WorldChangeListener(), main);
        manager.registerEvents(new InventoryListener(), main);
        manager.registerEvents(new SpawnElytra(), main);
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
