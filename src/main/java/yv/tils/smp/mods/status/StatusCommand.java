package yv.tils.smp.mods.status;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.internalapi.StringReplacer;
import yv.tils.smp.utils.configs.ConfigModeration;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.8.1
 */

public class StatusCommand implements CommandExecutor {

    List<String> defaultStatus = new ConfigModeration().ConfigRequest("StatusModule").getStringList("Default-Status");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {
        if (args.length < 1) {
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
                        if (new ConfigModeration().ConfigContentGet("StatusSave", String.valueOf(player1.getUniqueId())) != null) {

                            List<String> list1 = new ArrayList();
                            List<String> list2 = new ArrayList();
                            list1.add("PLAYER");
                            list2.add(player1.getName());

                            if (player2 != null) {
                                if (player2.isOnline()) {
                                    player2.setDisplayName(player2.getName());
                                    player2.setPlayerListName(player2.getName());
                                    new NametagManager().removePlayer(player2);
                            }}

                            clearStatus(null, player1);
                            player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_CLEAR_OTHER_CLEARED), list1, list2));
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
                if (args.length >= 2) {

                    StringBuilder builder = new StringBuilder();
                    for (int x = 1; x < args.length; x++) {
                        builder.append(args[x]).append(" ");
                    }
                    String status = builder.toString();

                    if (ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', status)).length() > new ConfigModeration().ConfigRequest("StatusModule").getInt("MaxStatusLength")) {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_CUSTOM_STATUS_TOO_LONG));
                        return true;
                    }
                    if (sender.hasPermission("yvtils.smp.command.status.set")) {
                        player.setDisplayName(ChatColor.translateAlternateColorCodes('&', status) + " " + player.getName());
                        player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', status) + " " + player.getName());
                        new NametagManager().addPlayer(player, ChatColor.translateAlternateColorCodes('&', status) + " " );
                        saveStatus(player, status);
                    }else {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_NOT_ALLOWED_TO_SET_CUSTOM_STATUS));
                    }
                }else {
                    sendUsage(sender);
                }}
            case "default" -> {
                for (String s : defaultStatus) {

                    StringBuilder builder = new StringBuilder();

                    for (int x = 1; x < args.length; x++) {
                        builder.append(args[x]).append(" ");
                    }

                    String status = builder.toString().substring(0, builder.toString().length() -1);

                    if (!defaultStatus.contains(status)) {
                        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_NO_DEFAULT_STATUS));
                        break;
                    }
                    if (status.equals(s)) {
                        player.setDisplayName(ChatColor.translateAlternateColorCodes('&', s) + " " + player.getName());
                        player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', s) + " " + player.getName());
                        new NametagManager().addPlayer(player, ChatColor.translateAlternateColorCodes('&', s) + " ");
                        saveStatus(player, s);
                    }}}
            default -> {
                sendUsage(sender);
            }

        }
        return false;
    }

    private void sendUsage(CommandSender sender){
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/status <set; default> <Prefix> (Color Codes are Supported!)\n" +
                "/status clear [Player]");
    }

    private void saveStatus(Player player, String status) {

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("STATUS");
        list2.add(status);

        String args = new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_SET), list1, list2);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', args));
        Bukkit.getConsoleSender().sendMessage(player.getName() + ": " + new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_SET), list1, list2));
        new ConfigModeration().ConfigContentAdd("StatusSave", String.valueOf(player.getUniqueId()), status);
    }

    private void clearStatus(Player player, OfflinePlayer player1) {
        if (player == null) {
            new ConfigModeration().ConfigContentRemove("StatusSave", String.valueOf(player1.getUniqueId()));
        } else {
            player.sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_STATUS_CLEAR_CLEARED));
            new ConfigModeration().ConfigContentRemove("StatusSave", String.valueOf(player.getUniqueId()));
        }}}