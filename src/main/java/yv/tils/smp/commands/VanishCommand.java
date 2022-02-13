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
import yv.tils.smp.Placeholder.LanguagePlaceholder;
import yv.tils.smp.SMPPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class VanishCommand implements CommandExecutor, Listener {
    private List<UUID> pickup = new ArrayList();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();
            String playerName = player.getName();
            List<String> list2 = SMPPlugin.getInstance().getConfig().getStringList("JoinMessage");
            List<String> list3 = SMPPlugin.getInstance().getConfig().getStringList("QuitMessage");

            for (int i = 0; i < list2.size(); i++) {
                list2.set(i, list2.get(i).replace("player", playerName));
            }
            Collections.shuffle(list2);
            String joinm = list2.get(0);

            for (int i = 0; i < list3.size(); i++) {
                list3.set(i, list3.get(i).replace("player", playerName));
            }
            Collections.shuffle(list3);
            String Quitm = list3.get(0);

            if (args.length == 0) {
                if (SMPPlugin.getInstance().vanished.contains(uuid)) {
                    SMPPlugin.getInstance().vanished.remove(uuid);
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.showPlayer(player);
                    }
                    player.setSleepingIgnored(false);
                    player.setCanPickupItems(true);
                    sender.sendMessage(LanguagePlaceholder.VanishOff());
                    Bukkit.broadcastMessage(joinm);
                } else {
                    SMPPlugin.getInstance().vanished.add(uuid);
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.hidePlayer(player);
                    }
                    player.setSleepingIgnored(true);
                    player.setCanPickupItems(false);
                    sender.sendMessage(LanguagePlaceholder.VanishOn());
                    Bukkit.broadcastMessage(Quitm);
                }
            } else {
                switch (args[0].toLowerCase()) {
                    case "itempickup":
                        if (!SMPPlugin.getInstance().vanished.contains(uuid)) {
                            sender.sendMessage(LanguagePlaceholder.VanishTiPu());
                            return true;
                        }
                        if (pickup.contains(uuid)) {
                            pickup.remove(uuid);
                            player.setCanPickupItems(false);
                            sender.sendMessage(LanguagePlaceholder.VanishTiPuOff());
                        } else {
                            pickup.add(uuid);
                            player.setCanPickupItems(true);
                            sender.sendMessage(LanguagePlaceholder.VanishTiPuOn());
                        }
                        break;
                    case "on":
                        if (SMPPlugin.getInstance().vanished.contains(uuid)) {
                            sender.sendMessage(LanguagePlaceholder.VanishAllreadyOn());
                            return true;
                        }

                        SMPPlugin.getInstance().vanished.add(uuid);
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.hidePlayer(player);
                        }
                        player.setSleepingIgnored(true);
                        player.setCanPickupItems(false);
                        sender.sendMessage(LanguagePlaceholder.VanishOn());
                        Bukkit.broadcastMessage(Quitm);
                        break;
                    case "off":
                        if (!SMPPlugin.getInstance().vanished.contains(uuid)) {
                            sender.sendMessage(LanguagePlaceholder.VanishAlreadyOff());
                            return false;
                        }

                        SMPPlugin.getInstance().vanished.remove(uuid);
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            target.showPlayer(player);
                        }
                        player.setSleepingIgnored(false);
                        player.setCanPickupItems(true);
                        sender.sendMessage(LanguagePlaceholder.VanishOff());
                        Bukkit.broadcastMessage(joinm);
                        break;
                    case "help":
                        sendUsage(sender);
                        break;
                    default:
                        sendUsage(sender);
                }
            }
        }

       // }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(LanguagePlaceholder.CommandUsage() + ChatColor.BLUE + "/v [itempickup, on, off, help]");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (SMPPlugin.getInstance().vanished.contains(player.getUniqueId())) {
                    event.setCancelled(true);
            }
        }
    }

}
