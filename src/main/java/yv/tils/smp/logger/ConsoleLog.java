package yv.tils.smp.logger;

import org.bukkit.Bukkit;
import yv.tils.smp.SMPPlugin;

public class ConsoleLog {
    public void ConsoleLog(String WhatLog) {
        if (SMPPlugin.getInstance().getConfig().getBoolean("Debug")) {
            Bukkit.getConsoleSender().sendMessage("§c----- DEBUG START -----");
            Bukkit.getConsoleSender().sendMessage(WhatLog);
            Bukkit.getConsoleSender().sendMessage("§c----- DEBUG END -----");
        }
    }
}
