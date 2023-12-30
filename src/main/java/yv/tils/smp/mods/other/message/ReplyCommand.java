package yv.tils.smp.mods.other.message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yv.tils.smp.YVtils;
import yv.tils.smp.placeholder.Prefix;
import yv.tils.smp.utils.color.HexSupport;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.internalapi.StringReplacer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class ReplyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 1) {
                if (YVtils.getInstance().getRecentMessages().containsKey(player.getUniqueId())) {
                    UUID uuid = YVtils.getInstance().getRecentMessages().get(player.getUniqueId());
                    if (Bukkit.getPlayer(uuid) != null) {
                        Player target = Bukkit.getPlayer(uuid);
                        StringBuilder builder = new StringBuilder();
                        for (String arg : args) {
                            builder.append(arg).append(" ");
                        }

                        String coloredMessage = HexSupport.hex(builder.toString());

                        List<String> list2 = YVtils.getInstance().getConfig().getStringList("DirectMessage.Design");
                        Collections.shuffle(list2);

                        for (int i = 0; i < list2.size(); i++) {
                            list2.set(i, list2.get(i).replace("receiver", target.getName()));
                            list2.set(i, list2.get(i).replace("sender", player.getName()));
                        }

                        String senderReceiverDesign = list2.get(0);

                        if (player.getUniqueId() == target.getUniqueId()) {
                            List<String> list4 = new ArrayList<>();
                            List<String> list3 = new ArrayList<>();
                            list3.add("MESSAGE");
                            list4.add(coloredMessage);

                            player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MSG_NOTE), list3, list4));
                        }else {
                            player.sendMessage(senderReceiverDesign + " " + coloredMessage);
                            target.sendMessage(senderReceiverDesign + " " + coloredMessage);

                            YVtils.getInstance().getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                            YVtils.getInstance().getRecentMessages().put(target.getUniqueId(), player.getUniqueId());
                        }
                    }else {
                        List<String> list3 = new ArrayList<>();
                        List<String> list4 = new ArrayList<>();
                        list3.add("PREFIX");
                        list4.add(Prefix.PREFIX);
                        player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MSG_PLAYER_WENT_OFFLINE), list3, list4));
                    }
                }else {
                    List<String> list3 = new ArrayList<>();
                    List<String> list4 = new ArrayList<>();
                    list3.add("PREFIX");
                    list4.add(Prefix.PREFIX);
                    player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MSG_HAVENT_MESSAGED_A_PLAYER), list3, list4));
                }}else {
                List<String> list3 = new ArrayList<>();
                List<String> list4 = new ArrayList<>();
                list3.add("PREFIX");
                list4.add(Prefix.PREFIX);
                player.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLAYER_UNKNOWN), list3, list4));
            }}else {
            sendUsage(sender);
        }
        return false;
    }

    private void sendUsage(CommandSender sender){
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE +
                "/r <message>");
    }
}