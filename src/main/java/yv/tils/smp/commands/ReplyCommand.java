package yv.tils.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.ColorCode;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class ReplyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 1) {

                    if (SMPPlugin.getInstance().getRecentMessages().containsKey(player.getUniqueId())) {
                        UUID uuid = SMPPlugin.getInstance().getRecentMessages().get(player.getUniqueId());
                        if (Bukkit.getPlayer(uuid) != null) {
                            Player target = Bukkit.getPlayer(uuid);
                            StringBuilder builder = new StringBuilder();
                            for (String arg : args) {
                                builder.append(arg).append(" ");
                            }
                            String colorcodereplace = new ColorCode().ColorCodes(builder.toString());

                            List<String> list2 = SMPPlugin.getInstance().getConfig().getStringList("DirectMessage.Design");

                            for (int i = 0; i < list2.size(); i++) {
                                list2.set(i, list2.get(i).replace("receiver", target.getName()));
                                list2.set(i, list2.get(i).replace("sender", sender.getName()));
                            }

                            Collections.shuffle(list2);
                            String senderreciverdesign = list2.get(0);


                            if (player.getUniqueId() == target.getUniqueId()) {
                                List<String> list4 = new ArrayList();
                                List<String> list3 = new ArrayList();
                                list3.add("MESSAGE");
                                list4.add(colorcodereplace);

                                player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MSG_NOTE), list3, list4));
                            }else {
                                player.sendMessage(senderreciverdesign + " " + colorcodereplace);
                                target.sendMessage(senderreciverdesign + " " + colorcodereplace);

                                SMPPlugin.getInstance().getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                            }
                        }else {
                            List<String> list3 = new ArrayList();
                            List<String> list4 = new ArrayList();
                            list3.add("PREFIX");
                            list4.add(MessagePlaceholder.PREFIX);
                            player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MSG_PLAYER_WENT_OFFLINE), list3, list4));
                        }
                    }else {
                        List<String> list3 = new ArrayList();
                        List<String> list4 = new ArrayList();
                        list3.add("PREFIX");
                        list4.add(MessagePlaceholder.PREFIX);
                        player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MSG_HAVENT_MESSAGED_A_PLAYER), list3, list4));
                    }}else {
                    List<String> list3 = new ArrayList();
                    List<String> list4 = new ArrayList();
                    list3.add("PREFIX");
                    list4.add(MessagePlaceholder.PREFIX);
                    player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLAYER_UNKNOWN), list3, list4));
                }}else {
                sendUsage(sender);
            }
        return false;
    }

    private void sendUsage(CommandSender sender){
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/r <message> <- Color Codes accepted! Use them with '&'!");
    }}