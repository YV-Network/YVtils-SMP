package de.yvtils.ba.commands;

import de.yvtils.ba.Main;
import de.yvtils.ba.Placeholder.MessagePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatMuteCommand implements CommandExecutor, Listener {
    public boolean enabled = true;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("yvtils.ba.command.mutechat")) {
            if (!enabled) {
                this.enabled = true;
                sender.sendMessage(MessagePlaceholder.PREFIXFEEDBACK + " You disabled the Chat");
                Bukkit.broadcastMessage(MessagePlaceholder.PREFIXGLOBALMUTE + " " +  Main.getInstance().getConfig().getString("Globalmute.Enable"));

            }else {
                this.enabled = false;
                sender.sendMessage(MessagePlaceholder.PREFIXFEEDBACK + " You enabled the Chat");
                Bukkit.broadcastMessage(MessagePlaceholder.PREFIXGLOBALMUTE + " " + Main.getInstance().getConfig().getString("Globalmute.Diasable"));
            }
        }
        return enabled;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (this.enabled) {
            if (!e.getPlayer().hasPermission("yvtils.ba.bypass.mutechat")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(MessagePlaceholder.PREFIXGLOBALMUTE + " Globalmute is activated!");
            }
        }
    }
}
