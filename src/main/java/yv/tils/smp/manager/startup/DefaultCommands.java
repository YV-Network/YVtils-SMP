package yv.tils.smp.manager.startup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.YVtils;
import yv.tils.smp.commands.FlyCommand;
import yv.tils.smp.commands.GlobalMuteCommand;
import yv.tils.smp.commands.GodCommand;
import yv.tils.smp.commands.VanishCommand;
import yv.tils.smp.commands.FlyWalkSpeed;
import yv.tils.smp.commands.ModerationCommand;
import yv.tils.smp.mods.other.message.*;
import yv.tils.smp.commands.autocompleter.*;
import yv.tils.smp.commands.replacecommands.*;
import yv.tils.smp.manager.commands.*;
import yv.tils.smp.mods.admin.invsee.EcSee;
import yv.tils.smp.mods.admin.invsee.InvSee;

/**
 * @since 4.6.8
 * @version CH2-1.0.0
 */
public class DefaultCommands {
    YVtils main = YVtils.getInstance();


    FlyCommand flyCommand = new FlyCommand();
    VanishCommand vanishCommand = new VanishCommand();
    GodCommand godCommand = new GodCommand();

/*
                    |           |
                    | Chapter 2 |
                    v           v
*/

    Gamemode gamemode = new Gamemode();
    MessageCommand message = new MessageCommand();
    ReplyCommand reply = new ReplyCommand();
    Start start = new Start();
    Heal heal = new Heal();
    Maintenance maintenance = new Maintenance();
    GlobalMute globalMute = new GlobalMute();

    public void registerCommands() {
        main.getCommand("fly").setExecutor(flyCommand);
        main.getCommand("vanish").setExecutor(vanishCommand);
        main.getCommand("flywalkspeed").setExecutor(new FlyWalkSpeed());
        main.getCommand("moderation").setExecutor(new ModerationCommand());
        main.getCommand("god").setExecutor(godCommand);
        main.getCommand("invsee").setExecutor(new InvSee());
        main.getCommand("ecsee").setExecutor(new EcSee());

/*
                    |           |
                    | Chapter 2 |
                    v           v
*/

        main.getCommand("gm").setExecutor(gamemode);
        main.getCommand("dm").setExecutor(message);
        main.getCommand("reply").setExecutor(reply);
        main.getCommand("start").setExecutor(start);
        main.getCommand("heal").setExecutor(heal);
        main.getCommand("maintenance").setExecutor(maintenance);
        main.getCommand("globalmute").setExecutor(globalMute);
    }

    public void registerTabCompleter() {
        main.getCommand("moderation").setTabCompleter(new ModerationAutoCompleter());
        main.getCommand("flywalkspeed").setTabCompleter(new FlyWalkSpeedAutoCompleter());
        main.getCommand("vanish").setTabCompleter(new VanishAutoCompleter());
        main.getCommand("maintenance").setTabCompleter(new MainteanceAutoCompleter());

/*
                    |           |
                    | Chapter 2 |
                    v           v
*/

        main.getCommand("gm").setTabCompleter(gamemode);
        main.getCommand("dm").setTabCompleter(message);
        main.getCommand("maintenance").setTabCompleter(maintenance);
        main.getCommand("globalmute").setTabCompleter(globalMute);
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
            //manager.registerEvents(new msgCommand(), main);
        }
        if (main.getConfig().getBoolean("CommandBlock./kick")) {
            manager.registerEvents(new KickCommand(), main);
        }
        if (main.getConfig().getBoolean("CommandBlock./ban")) {
            manager.registerEvents(new BanCommand(), main);
        }
    }
}
