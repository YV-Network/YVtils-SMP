package yv.tils.smp.utils.internalapi;

import org.bukkit.Bukkit;
import yv.tils.smp.YVtils;

/**
 * @version CH2-1.0.0
 * @since 4.6.6
 */
public class Log {
    private static int i = 1;

    public Log(String WhatLog) {
        if (YVtils.getInstance().getConfig().getBoolean("Debug")) {
            if (i == 1) {
                Bukkit.getConsoleSender().sendMessage("----- INCLUDE IN REPORTS -----");
                Bukkit.getConsoleSender().sendMessage("Plugin: YVtils-SMP");
                Bukkit.getConsoleSender().sendMessage("Plugin Version: " + Variables.PluginVersion);
                Bukkit.getConsoleSender().sendMessage("Server Version: " + Bukkit.getVersion());
                Bukkit.getConsoleSender().sendMessage("Server Software: " + Bukkit.getName());
                Bukkit.getConsoleSender().sendMessage("Loaded Mods: \n" + new Runtime().loadedMods());
                Bukkit.getConsoleSender().sendMessage("----- INCLUDE IN REPORTS -----");
                i++;
            }

            Bukkit.getConsoleSender().sendMessage("----- DEBUG START -----");
            Bukkit.getConsoleSender().sendMessage("YVtils-SMP Plugin");
            Bukkit.getConsoleSender().sendMessage(WhatLog);
            Bukkit.getConsoleSender().sendMessage("----- DEBUG END -----");
        }
    }
}
