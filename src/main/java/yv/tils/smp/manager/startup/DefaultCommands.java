package yv.tils.smp.manager.startup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import yv.tils.smp.YVtils;
import yv.tils.smp.commands.autocompleter.VanishAutoCompleter;
import yv.tils.smp.manager.commands.*;
import yv.tils.smp.manager.listener.CommandPreprocess;
import yv.tils.smp.mods.admin.invsee.EcSee;
import yv.tils.smp.mods.admin.invsee.InvSee;
import yv.tils.smp.mods.admin.moderation.Moderation;
import yv.tils.smp.mods.admin.moderation.TempBan;
import yv.tils.smp.mods.admin.vanish.Vanish;
import yv.tils.smp.mods.other.message.MessageCommand;
import yv.tils.smp.mods.other.message.ReplyCommand;

/**
 * @since 4.6.8
 * @version CH2-1.0.0
 */
public class DefaultCommands {
    YVtils main = YVtils.getInstance();

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
    Vanish vanish = new Vanish();
    Speed speed = new Speed();
    Moderation moderation = new Moderation();
    TempBan tempBan = new TempBan();
    Fly fly = new Fly();
    God god = new God();

    public void registerCommands() {
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
        main.getCommand("vanish").setExecutor(vanish);
        main.getCommand("speed").setExecutor(speed);
        main.getCommand("moderation").setExecutor(moderation);
        main.getCommand("tempban").setExecutor(tempBan);
        main.getCommand("fly").setExecutor(fly);
        main.getCommand("god").setExecutor(god);
    }

    public void registerTabCompleter() {
        main.getCommand("vanish").setTabCompleter(new VanishAutoCompleter());

/*
                    |           |
                    | Chapter 2 |
                    v           v
*/

        main.getCommand("gm").setTabCompleter(gamemode);
        main.getCommand("dm").setTabCompleter(message);
        main.getCommand("maintenance").setTabCompleter(maintenance);
        main.getCommand("globalmute").setTabCompleter(globalMute);
        main.getCommand("speed").setTabCompleter(speed);
        main.getCommand("moderation").setTabCompleter(moderation);
        main.getCommand("tempban").setTabCompleter(tempBan);
    }

    public void registerCommandReplace() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new CommandPreprocess(), main);
    }
}
