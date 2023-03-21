package yv.tils.smp.manager.register;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.commands.*;
import yv.tils.smp.commands.autocompleter.*;
import yv.tils.smp.commands.replacecommands.*;
import yv.tils.smp.modules.adminstration.invsee.EcSee;
import yv.tils.smp.modules.adminstration.invsee.InvSee;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class DefaultCommands {
    SMPPlugin main = SMPPlugin.getInstance();

    GlobalMuteCommand globalMuteCommand = new GlobalMuteCommand();
    FlyCommand flyCommand = new FlyCommand();
    VanishCommand vanishCommand = new VanishCommand();
    GodCommand godCommand = new GodCommand();

    public void registerCommands() {
        main.getCommand("chatmute").setExecutor(globalMuteCommand);
        main.getCommand("fly").setExecutor(flyCommand);
        main.getCommand("vanish").setExecutor(vanishCommand);
        main.getCommand("flywalkspeed").setExecutor(new FlyWalkSpeed());
        main.getCommand("start").setExecutor(new StartCommand());
        main.getCommand("moderation").setExecutor(new ModerationCommand());
        main.getCommand("gm").setExecutor(new GamemodeCommand());
        main.getCommand("directmessage").setExecutor(new MessageCommand());
        main.getCommand("reply").setExecutor(new ReplyCommand());
        main.getCommand("god").setExecutor(godCommand);
        main.getCommand("heal").setExecutor(new HealCommand());
        main.getCommand("maintenance").setExecutor(new MaintenanceCommand());
        main.getCommand("invsee").setExecutor(new InvSee());
        main.getCommand("ecsee").setExecutor(new EcSee());
    }

    public void registerTabCompleter() {
        main.getCommand("moderation").setTabCompleter(new ModerationAutoCompleter());
        main.getCommand("flywalkspeed").setTabCompleter(new FlyWalkSpeedAutoCompleter());
        main.getCommand("gm").setTabCompleter(new GamemodeAutoCompleter());
        main.getCommand("vanish").setTabCompleter(new VanishAutoCompleter());
        main.getCommand("maintenance").setTabCompleter(new MainteanceAutoCompleter());
    }

    public void registerCommandReplace() {
        PluginManager manager = Bukkit.getPluginManager();
        if (main.getConfig().getBoolean("CommandBlock./seed")) {
            manager.registerEvents(new seedcommand(), main);
        }
        if (main.getConfig().getBoolean("CommandBlock./pardon")) {
            manager.registerEvents(new pardonCommand(), main);
        }
        if (main.getConfig().getBoolean("CommandBlock./msg")) {
            manager.registerEvents(new msgCommand(), main);
        }
        if (main.getConfig().getBoolean("CommandBlock./kick")) {
            manager.registerEvents(new KickCommand(), main);
        }
        if (main.getConfig().getBoolean("CommandBlock./ban")) {
            manager.registerEvents(new BanCommand(), main);
        }
    }
}
