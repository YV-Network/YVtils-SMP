package yv.tils.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import yv.tils.smp.SMPPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class MaintenanceCommand implements CommandExecutor {

    File file2 = new File(SMPPlugin.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration dontedit = YamlConfiguration.loadConfiguration(file2);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if(args.length == 0) {
            sendUsage(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "false", "off" -> {
                if (dontedit.getString("MaintenanceMode").equals("true")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is now §adeactivated§7!", "§7Der Mainteance Modus ist nun §adeaktiviert§7!"));
                    Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is now §adeactivated§7!", "§7Der Mainteance Modus ist nun §adeaktiviert§7!"));
                    SMPPlugin.getInstance().maintenances = false;
                    dontedit.set("MaintenanceMode", "false");
                    try {
                        dontedit.save(file2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (dontedit.getString("MaintenanceMode").equals("false")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Maintenance Mode is already §adeactivated§7!", "§7Der Maintenance Modus ist bereits §adeaktiviert§7!"));
                }else {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("This Config Value is not allowed!", "Dieser Config Wert ist nicht zulässig!"));
                }}
            case "true", "on" -> {
                if (dontedit.getString("MaintenanceMode").equals("true")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Maintenance Mode is already §aactivated§7!", "§7Der Maintenance Modus ist bereits §aaktiviert§7!"));
                }else if (dontedit.getString("MaintenanceMode").equals("false")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Maintenance Mode is now §aactivated§7!", "§7Der Maintenance Modus ist nun §aaktiviert§7!"));
                    Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.DirectFormatter("§7The Maintenance Mode is now §aactivated§7!", "§7Der Maintenance Modus ist nun §aaktiviert§7!"));
                    SMPPlugin.getInstance().maintenances = true;
                    dontedit.set("MaintenanceMode", "true");
                    try {
                        dontedit.save(file2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission("yvtils.smp.maintenance.join")) {}else {
                            player1.kickPlayer("Yeah");
                        }
                    }
                }else {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("This Config Value is not allowed!", "Dieser Config Wert ist nicht zulässig!"));
                }}
            case "toggle" -> {
                if (dontedit.getString("MaintenanceMode").equals("true")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Maintenance Mode is now §adeactivated§7!", "§7Der Maintenance Modus ist nun §adeaktiviert§7!"));
                    Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.DirectFormatter("§7The Maintenance Mode is now §adeactivated§7!", "§7Der Maintenance Modus ist nun §adeaktiviert§7!"));
                    SMPPlugin.getInstance().maintenances = false;
                    dontedit.set("MaintenanceMode", "false");
                    try {
                        dontedit.save(file2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (dontedit.getString("MaintenanceMode").equals("false")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Maintenance Mode is now §aactivated§7!", "§7Der Maintenance Modus ist nun §aaktiviert§7!"));
                    Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.DirectFormatter("§7The Maintenance Mode is now §aactivated§7!", "§7Der Maintenance Modus ist nun §aaktiviert§7!"));
                    SMPPlugin.getInstance().maintenances = true;
                    dontedit.set("MaintenanceMode", "true");
                    try {
                        dontedit.save(file2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission("yvtils.smp.maintenance.join")) {}else {
                            player1.kickPlayer("Yeah");
                        }
                    }
                }else {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("This Config Value is not allowed!", "Dieser Config Wert ist nicht zulässig!"));
                }}
            case "status" -> {
                if (dontedit.get("MaintenanceMode") != null) {
                    if (dontedit.getString("MaintenanceMode").equals("true")) {
                        sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Maintenance Mode is §aactivated§7!", "§7Der Maintenance Modus ist §aaktiviert§7!"));
                    }else if (dontedit.getString("MaintenanceMode").equals("false")) {
                        sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Maintenance Mode is §adeactivated§7!", "§7Der Maintenance Modus ist §adeaktiviert§7!"));
                    }else {
                        sender.sendMessage(LanguagePlaceholder.DirectFormatter("This Config Value is not allowed!", "Dieser Config Wert ist nicht zulässig!"));
                    }}}
            default -> sendUsage(sender);
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguagePlaceholder.CommandUsage() + ChatColor.BLUE +
                "/mainteance [true, false, toggle, status]");
    }}