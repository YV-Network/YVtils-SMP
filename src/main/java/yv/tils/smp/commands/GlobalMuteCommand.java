package yv.tils.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import yv.tils.smp.LanguageSystem.LanguageFile;
import yv.tils.smp.LanguageSystem.LanguageMessage;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.placeholder.StringReplacer;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class GlobalMuteCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        List<String> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        list1.add("PREFIXFEEDBACK");
        list2.add(MessagePlaceholder.PREFIXFEEDBACK);

        List<String> list3 = new ArrayList();
        List<String> list4 = new ArrayList();
        list3.add("PREFIXGLOBALMUTE");
        list4.add(MessagePlaceholder.PREFIXGLOBALMUTE);

        if (sender.hasPermission("yvtils.smp.command.mutechat")) {
            if (!SMPPlugin.getInstance().globalmute) {
                SMPPlugin.getInstance().globalmute = true;
                sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.CHATMUTE_ENABLE_FEEDBACK), list1, list2));
                Bukkit.broadcastMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.CHATMUTE_ENABLE_ANNOUNCEMENT), list3, list4));
            }else {
                SMPPlugin.getInstance().globalmute = false;
                sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.CHATMUTE_DISABLE_FEEDBACK), list1, list2));
                Bukkit.broadcastMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.CHATMUTE_DISABLE_ANNOUNCEMENT), list3, list4));
            }}
        return SMPPlugin.getInstance().globalmute;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        List<String> list1 = new ArrayList();
        List<String> list2 = new ArrayList();
        list1.add("PREFIXGLOBALMUTE");
        list2.add(MessagePlaceholder.PREFIXGLOBALMUTE);

        if (SMPPlugin.getInstance().globalmute) {
            if (!e.getPlayer().hasPermission("yvtils.smp.bypass.mutechat")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.CHATMUTE_TRY_TO_WRITE), list1, list2));
            }}}}