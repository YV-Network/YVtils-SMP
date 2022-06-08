package yv.tils.smp.modules.status;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.LanguageSystem.LanguageFile;
import yv.tils.smp.LanguageSystem.LanguageMessage;
import yv.tils.smp.placeholder.ColorCode;
import yv.tils.smp.utils.ConfigModeration;

import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.6
 */

public class StatusCommand implements CommandExecutor {

    List<String> defaultstatus = new ConfigModeration().ConfigRequest("StatusModule").getStringList("Default-Status");
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        if (args.length < 2) {
            sendUsage(sender);
            return true;
        }

        Player player = (Player) sender;

        switch (args[0].toLowerCase()) {
            case "clear" -> {
                if (args.length == 2) {
                    OfflinePlayer player1 = Bukkit.getOfflinePlayer(args[1]);
                    Player player2 = Bukkit.getPlayer(args[1]);
                    if (sender.hasPermission("yvtils.smp.command.status.clear.other")) {
                        if (new ConfigModeration().ConfigContentGet("StatusSave", String.valueOf(player.getUniqueId())) != null) {
                                player2.setDisplayName(player2.getName());
                                player2.setPlayerListName(player2.getName());
                                new NametagManager().removePlayer(player2);
                                clearStatus(null, player1);
                            } else {
                                sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_OTHER_PLAYER_HAS_NO_STATUS));
                            }
                    }else {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_CLEAR_OTHER_UNALLOWED));
                    }}else {
                    if (new ConfigModeration().ConfigContentGet("StatusSave", String.valueOf(player.getUniqueId())) != null) {
                        player.setDisplayName(player.getName());
                        player.setPlayerListName(player.getName());
                        new NametagManager().removePlayer(player);
                        clearStatus(player, null);
                    }else {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_PLAYER_HAS_NO_STATUS));
                    }}}
            case "set" -> {
                if (args.length == 2) {
                    if (args[1].length() > new ConfigModeration().ConfigRequest("StatusModule").getInt("MaxStatusLength")) {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_CUSTOM_STATUS_TOO_LONG));
                    }
                    if (sender.hasPermission("yvtils.smp.command.status.set")) {
                        player.setDisplayName(new ColorCode().ColorCodes(args[1]) + " " + player.getName());
                        player.setPlayerListName(new ColorCode().ColorCodes(args[1]) + " " + player.getName());
                        new NametagManager().addPlayer(player, new ColorCode().ColorCodes(args[1]) + " " );
                        saveStatus(player, args[1]);
                    }else {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_NOT_ALLOWED_TO_SET_CUSTOM_STATUS));
                    }
                }else {
                    sendUsage(sender);
                }}
            case "default" -> {
                for (String s : defaultstatus) {
                    if (!defaultstatus.contains(args[1])) {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_NO_DEFAULT_STATUS));
                        break;
                    }
                    if (args[1].equals(s)) {
                        player.setDisplayName(new ColorCode().ColorCodes(s) + " " + player.getName());
                        player.setPlayerListName(new ColorCode().ColorCodes(s) + " " + player.getName());
                        new NametagManager().addPlayer(player, new ColorCode().ColorCodes(s) + " ");
                        saveStatus(player, s);
                    }}}
            default -> sendUsage(sender);

        }
        return false;
    }

    private void sendUsage(CommandSender sender){
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/status <set; default> <Prefix> (Color Codes are Supported!)\n" +
                "/status clear [Player]");
    }

    private void saveStatus(Player player, String status) {
        new ConfigModeration().ConfigContentAdd("StatusSave", String.valueOf(player.getUniqueId()), status);
    }

    private void clearStatus(Player player, OfflinePlayer player1) {
        if (player == null) {
            new ConfigModeration().ConfigContentRemove("StatusSave", String.valueOf(player1.getUniqueId()));
        } else {
            new ConfigModeration().ConfigContentRemove("StatusSave", String.valueOf(player.getUniqueId()));
        }}}