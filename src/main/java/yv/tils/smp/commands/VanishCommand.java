package yv.tils.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import yv.tils.smp.YVtils;
import yv.tils.smp.placeholder.Prefix;
import yv.tils.smp.internalapi.StringReplacer;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @BecomingRework 4.6.9
 * @since 4.6.6
 * @version 4.6.6
 */
public class VanishCommand implements CommandExecutor, Listener {
    private final List<UUID> pickup = new ArrayList();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            UUID uuid = player.getUniqueId();
            String playerName = player.getName();
            List<String> list1 = YVtils.getInstance().getConfig().getStringList("JoinMessage");
            List<String> list2 = YVtils.getInstance().getConfig().getStringList("QuitMessage");

            list1.replaceAll(s -> s.replace("player", playerName));
            Collections.shuffle(list1);
            String joinm = list1.get(0);

            list2.replaceAll(s -> s.replace("player", playerName));
            Collections.shuffle(list2);
            String Quitm = list2.get(0);

            List<String> list3 = new ArrayList<>();
            List<String> list4 = new ArrayList<>();
            list1.add("PREFIX");
            list2.add(Prefix.PREFIX);


            if (args.length == 0) {
                if (YVtils.getInstance().vanished.contains(uuid)) {
                    YVtils.getInstance().vanished.remove(uuid);
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.showPlayer(player);
                    }
                    player.setSleepingIgnored(false);
                    player.setCanPickupItems(true);
                    sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.VANISH_DEACTIVATE), list3, list4));
                    Bukkit.broadcastMessage(joinm);
                } else {
                    YVtils.getInstance().vanished.add(uuid);
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.hidePlayer(player);
                    }
                    player.setSleepingIgnored(true);
                    player.setCanPickupItems(false);
                    sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.VANISH_ACTIVATE), list3, list4));
                    Bukkit.broadcastMessage(Quitm);
                }} else {
                switch (args[0].toLowerCase()) {
                    case "itempickup":
                        if (!YVtils.getInstance().vanished.contains(uuid)) {
                            sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.VANISH_ITEM_PICKUP_NOT_IN_VANISH), list3, list4));
                            return true;
                        }
                        if (pickup.contains(uuid)) {
                            pickup.remove(uuid);
                            player.setCanPickupItems(false);
                            sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.VANISH_ITEM_PICKUP_DEACTIVATE), list3, list4));
                        } else {
                            pickup.add(uuid);
                            player.setCanPickupItems(true);
                            sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.VANISH_ITEM_PICKUP_ACTIVATE), list3, list4));
                        }
                        break;
                    case "on":
                        if (YVtils.getInstance().vanished.contains(uuid)) {
                            sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.VANISH_ALREADY_ACTIVATED), list3, list4));
                            return true;
                        }

                        YVtils.getInstance().vanished.add(uuid);
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.hidePlayer(player);
                        }
                        player.setSleepingIgnored(true);
                        player.setCanPickupItems(false);
                        sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.VANISH_ACTIVATE), list3, list4));
                        Bukkit.broadcastMessage(Quitm);
                        break;
                    case "off":
                        if (!YVtils.getInstance().vanished.contains(uuid)) {
                            sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.VANISH_ALREADY_DEACTIVATED), list3, list4));
                            return false;
                        }

                        YVtils.getInstance().vanished.remove(uuid);
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.showPlayer(player);
                        }
                        player.setSleepingIgnored(false);
                        player.setCanPickupItems(true);
                        sender.sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.VANISH_DEACTIVATE), list3, list4));
                        Bukkit.broadcastMessage(joinm);
                        break;
                    default:
                        sendUsage(sender);
                }}}
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguageFile.getMessage(LanguageMessage.COMMAND_USAGE) + " " + ChatColor.BLUE + "/v [itempickup, on, off, help]");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
            if (event.getEntity() instanceof Player player) {
                if (YVtils.getInstance().vanished.contains(player.getUniqueId())) {
                    event.setCancelled(true);
            }}}}