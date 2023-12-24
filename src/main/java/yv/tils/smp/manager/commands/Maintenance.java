package yv.tils.smp.manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import yv.tils.smp.YVtils;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class Maintenance implements CommandExecutor, TabCompleter {

    File file = new File(YVtils.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(file);

    public static boolean maintenance = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String arg = "toggle";

        if (args.length != 0) {
            arg = args[0].toLowerCase();
        }

        switch (arg.toLowerCase()) {
            case "false", "off" -> {
                if (ymlFile.getString("MaintenanceMode").equals("true")) {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_DEACTIVATE));
                    Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_DEACTIVATE));
                    maintenance = false;
                    ymlFile.set("MaintenanceMode", "false");
                    try {
                        ymlFile.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (ymlFile.getString("MaintenanceMode").equals("false")) {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ALREADY_DEACTIVATED));
                }else {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ILLEGAL_CONFIG_VALUE));
                }
            }
            case "true", "on" -> {
                if (ymlFile.getString("MaintenanceMode").equals("true")) {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ALREADY_ACTIVATED));
                }else if (ymlFile.getString("MaintenanceMode").equals("false")) {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_ACTIVATE));
                    Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_ACTIVATE));
                    maintenance = true;
                    ymlFile.set("MaintenanceMode", "true");
                    try {
                        ymlFile.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        if (!player1.hasPermission("yvtils.smp.maintenance.join")) {
                            player1.kickPlayer(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_PLAYER_NOT_ALLOWED_TO_JOIN_KICK_MESSAGE));
                        }
                    }
                }else {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ILLEGAL_CONFIG_VALUE));
                }
            }
            case "toggle" -> {
                if (ymlFile.getString("MaintenanceMode").equals("true")) {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_DEACTIVATE));
                    Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_DEACTIVATE));
                    maintenance = false;
                    ymlFile.set("MaintenanceMode", "false");
                    try {
                        ymlFile.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (ymlFile.getString("MaintenanceMode").equals("false")) {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_ACTIVATE));
                    Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_COMMAND_ACTIVATE));
                    maintenance = true;
                    ymlFile.set("MaintenanceMode", "true");
                    try {
                        ymlFile.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        if (!player1.hasPermission("yvtils.smp.maintenance.join")) {
                            player1.kickPlayer(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_PLAYER_NOT_ALLOWED_TO_JOIN_KICK_MESSAGE));
                        }
                    }
                }else {
                    sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ILLEGAL_CONFIG_VALUE));
                }
            }
            case "status" -> {
                if (ymlFile.get("MaintenanceMode") != null) {
                    if (ymlFile.getString("MaintenanceMode").equals("true")) {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_STATUS_ENABLED));
                    }else if (ymlFile.getString("MaintenanceMode").equals("false")) {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_STATUS_DISABLED));
                    }else {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MAINTENANCE_ILLEGAL_CONFIG_VALUE));
                    }
                }
            }
            default -> sendUsage(sender);
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/mainteance <true, false, toggle, status>");
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            results.add("on");
            results.add("off");
            results.add("true");
            results.add("false");
            results.add("toggle");
            results.add("status");
        }
        return results;
    }
}