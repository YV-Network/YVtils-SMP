package yv.tils.smp.manager.startup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.YVtils;
import yv.tils.smp.commands.VanishCommand;
import yv.tils.smp.manager.listener.*;
import yv.tils.smp.mods.admin.logger.logger.*;
import yv.tils.smp.mods.other.SpawnElytra;

/**
 * @since 4.6.8
 * @version CH2-1.0.0
 */
public class DefaultListeners {
    YVtils main = YVtils.getInstance();

    VanishCommand vanishCommand = new VanishCommand();

    public void registerListener() {
        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(vanishCommand, main);

/*
                    |           |
                    | Chapter 2 |
                    v           v
*/

        manager.registerEvents(new JoinListener(), main);
        manager.registerEvents(new QuitListener(), main);
        manager.registerEvents(new ChatListener(), main);
        manager.registerEvents(new LoginListener(), main);
        manager.registerEvents(new ServerListPingListener(), main);
        manager.registerEvents(new AirTimeListener(), main);
        manager.registerEvents(new DamageListener(), main);
        manager.registerEvents(new WorldChangeListener(), main);
        manager.registerEvents(new InventoryListener(), main);
        manager.registerEvents(new SpawnElytra(), main);
        manager.registerEvents(new PlayerCommandPreprocess(), main);
        manager.registerEvents(new EntityTargetListener(), main);
        manager.registerEvents(new GamemodeSwitch(), main);
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
