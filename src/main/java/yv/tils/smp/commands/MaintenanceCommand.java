package yv.tils.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import yv.tils.smp.YVtils;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.io.File;
import java.io.IOException;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class MaintenanceCommand implements CommandExecutor {

    File file2 = new File(YVtils.getInstance().getDataFolder(), "DoNotEdit.yml");
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
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_DEACTIVATE));
                    Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_DEACTIVATE));
                    YVtils.getInstance().maintenances = false;
                    dontedit.set("MaintenanceMode", "false");
                    try {
                        dontedit.save(file2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (dontedit.getString("MaintenanceMode").equals("false")) {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ALREADY_DEACTIVATED));
                }else {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ILLEGAL_CONFIG_VALUE));
                }}
            case "true", "on" -> {
                if (dontedit.getString("MaintenanceMode").equals("true")) {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ALREADY_ACTIVATED));
                }else if (dontedit.getString("MaintenanceMode").equals("false")) {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_ACTIVATE));
                    Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_ACTIVATE));
                    YVtils.getInstance().maintenances = true;
                    dontedit.set("MaintenanceMode", "true");
                    try {
                        dontedit.save(file2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission("yvtils.smp.maintenance.join")) {}else {
                            player1.kickPlayer(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_PLAYER_NOT_ALLOWED_TO_JOIN_KICK_MESSAGE));
                        }
                    }
                }else {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ILLEGAL_CONFIG_VALUE));
                }}
            case "toggle" -> {
                if (dontedit.getString("MaintenanceMode").equals("true")) {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_DEACTIVATE));
                    Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_DEACTIVATE));
                    YVtils.getInstance().maintenances = false;
                    dontedit.set("MaintenanceMode", "false");
                    try {
                        dontedit.save(file2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (dontedit.getString("MaintenanceMode").equals("false")) {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_ACTIVATE));
                    Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_ACTIVATE));
                    YVtils.getInstance().maintenances = true;
                    dontedit.set("MaintenanceMode", "true");
                    try {
                        dontedit.save(file2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission("yvtils.smp.maintenance.join")) {}else {
                            player1.kickPlayer(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_PLAYER_NOT_ALLOWED_TO_JOIN_KICK_MESSAGE));
                        }
                    }
                }else {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ILLEGAL_CONFIG_VALUE));
                }}
            case "status" -> {
                if (dontedit.get("MaintenanceMode") != null) {
                    if (dontedit.getString("MaintenanceMode").equals("true")) {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_STATUS_ENABLED));
                    }else if (dontedit.getString("MaintenanceMode").equals("false")) {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_STATUS_DISABLED));
                    }else {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ILLEGAL_CONFIG_VALUE));
                    }}}
            default -> sendUsage(sender);
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/mainteance [true, false, toggle, status]");
    }}