package yv.tils.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.LanguagePlaceholder;

import java.io.File;
import java.io.IOException;

public class MainteanceCommand implements CommandExecutor {

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
                if (dontedit.getBoolean("MainteanceMode")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is now §adeactivated§7!", "§7Der Mainteance Modus ist nun §adeaktiviert§7!"));
                    Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is now §adeactivated§7!", "§7Der Mainteance Modus ist nun §adeaktiviert§7!"));
                    dontedit.set("MainteanceMode", false);
                }else if (!dontedit.getBoolean("MainteanceMode")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is already §adeactivated§7!", "§7Der Mainteance Modus ist bereits §adeaktiviert§7!"));
                }else {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("This Config Value is not allowed!", "Dieser Config Wert ist nicht zulässig!"));
                }}
            case "true", "on" -> {
                if (dontedit.getBoolean("MainteanceMode")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is already §aactivated§7!", "§7Der Mainteance Modus ist bereits §aaktiviert§7!"));
                }else if (!dontedit.getBoolean("MainteanceMode")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is now §aactivated§7!", "§7Der Mainteance Modus ist nun §aaktiviert§7!"));
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is now §aactivated§7!", "§7Der Mainteance Modus ist nun §aaktiviert§7!"));
                    dontedit.set("MainteanceMode", true);
                }else {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("This Config Value is not allowed!", "Dieser Config Wert ist nicht zulässig!"));
                }}
            case "toggle" -> {
                if (dontedit.getBoolean("MainteanceMode")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is now §adeactivated§7!", "§7Der Mainteance Modus ist nun §adeaktiviert§7!"));
                    Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is now §adeactivated§7!", "§7Der Mainteance Modus ist nun §adeaktiviert§7!"));
                    dontedit.set("MainteanceMode", false);
                }else if (!dontedit.getBoolean("MainteanceMode")) {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is now §aactivated§7!", "§7Der Mainteance Modus ist nun §aaktiviert§7!"));
                    Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is now §aactivated§7!", "§7Der Mainteance Modus ist nun §aan§7!"));
                    dontedit.set("MainteanceMode", true);
                }else {
                    sender.sendMessage(LanguagePlaceholder.DirectFormatter("This Config Value is not allowed!", "Dieser Config Wert ist nicht zulässig!"));
                }}
            case "status" -> {
                if (dontedit.get("MainteanceMode") != null) {
                    if (dontedit.getBoolean("MainteanceMode")) {
                        sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is §aactivated§7!", "§7Der Mainteance Modus ist §aaktiviert§7!"));
                    }else if (!dontedit.getBoolean("MainteanceMode")) {
                        sender.sendMessage(LanguagePlaceholder.DirectFormatter("§7The Mainteance Mode is §adeactivated§7!", "§7Der Mainteance Modus ist §adeaktiviert§7!"));
                    }else {
                        sender.sendMessage(LanguagePlaceholder.DirectFormatter("This Config Value is not allowed!", "Dieser Config Wert ist nicht zulässig!"));
                    }}}
            default -> sendUsage(sender);
        }
        try {
            dontedit.save(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguagePlaceholder.CommandUsage() + ChatColor.BLUE +
                "/mainteance [true, false, toggle, status]");
    }}