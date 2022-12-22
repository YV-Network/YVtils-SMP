package yv.tils.smp.logger;

import org.bukkit.Bukkit;
import yv.tils.smp.SMPPlugin;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class ConsoleLog {
    public ConsoleLog(String WhatLog) {
        if (SMPPlugin.getInstance().getConfig().getBoolean("Debug")) {
            Bukkit.getConsoleSender().sendMessage("§c----- DEBUG START -----");
            Bukkit.getConsoleSender().sendMessage("§9YVtils-SMP Plugin");
            Bukkit.getConsoleSender().sendMessage(WhatLog);
            Bukkit.getConsoleSender().sendMessage("§c----- DEBUG END -----");
        }
    }
}
