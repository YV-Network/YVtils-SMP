package yv.tils.smp.manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import yv.tils.smp.YVtils;
import yv.tils.smp.internalapi.StringReplacer;
import yv.tils.smp.mods.other.SpawnElytra;
import yv.tils.smp.placeholder.Prefix;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class GlobalMute implements CommandExecutor, TabCompleter {
    private static GlobalMute instance;
    public static GlobalMute getInstance() {return instance;}

    public static boolean globalmute = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        instance = this;

        String arg = "toggle";

        if (args.length != 0) {
            arg = args[0].toLowerCase();
        }

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("PREFIXFEEDBACK");
        list2.add(Prefix.PREFIXFEEDBACK);

        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        list3.add("PREFIXGLOBALMUTE");
        list4.add(Prefix.PREFIXGLOBALMUTE);

        switch (arg.toLowerCase()) {
            case "false", "off" -> {
                if (globalmute) {
                    globalmute = false;
                    sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_DISABLE_FEEDBACK), list1, list2));
                    Bukkit.broadcastMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_DISABLE_ANNOUNCEMENT), list3, list4));
                }else {
                    sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_ALREADY_DISABLED), list1, list2));
                }
            }
            case "true", "on" -> {
                if (!globalmute) {
                    globalmute = true;
                    sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_ENABLE_FEEDBACK), list1, list2));
                    Bukkit.broadcastMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_ENABLE_ANNOUNCEMENT), list3, list4));
                }else {
                    sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_ALREADY_ENABLED), list1, list2));
                }
            }
            case "toggle" -> {
                if (globalmute) {
                    globalmute = false;
                    sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_DISABLE_FEEDBACK), list1, list2));
                    Bukkit.broadcastMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_DISABLE_ANNOUNCEMENT), list3, list4));
                }else {
                    globalmute = true;
                    sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_ENABLE_FEEDBACK), list1, list2));
                    Bukkit.broadcastMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_ENABLE_ANNOUNCEMENT), list3, list4));
                }
            }
            case "status" -> {
                if (globalmute) {
                    sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_STATUS_ENABLED), list1, list2));
                }else {
                    sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_STATUS_DISABLED), list1, list2));
                }
            }
            default -> sendUsage(sender);
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE + "/globalmute <true, false, toggle, status>");
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

    public void onChat(AsyncPlayerChatEvent e) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("PREFIXGLOBALMUTE");
        list2.add(Prefix.PREFIXGLOBALMUTE);

        if (globalmute) {
            if (!e.getPlayer().hasPermission("yvtils.smp.bypass.mutechat")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.GLOBALMUTE_TRY_TO_WRITE), list1, list2));
            }
        }
    }
}