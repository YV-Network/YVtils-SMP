package yv.tils.smp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.MessagePlaceholder;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class ChatMuteCommand implements CommandExecutor, Listener {
    public boolean enabled = false;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("yvtils.smp.command.mutechat")) {
            if (!enabled) {
                this.enabled = true;
                sender.sendMessage(MessagePlaceholder.PREFIXFEEDBACK + " You disabled the Chat");
                Bukkit.broadcastMessage(MessagePlaceholder.PREFIXGLOBALMUTE + " " +  SMPPlugin.getInstance().getConfig().getString("Globalmute.Enable"));

            }else {
                this.enabled = false;
                sender.sendMessage(MessagePlaceholder.PREFIXFEEDBACK + " You enabled the Chat");
                Bukkit.broadcastMessage(MessagePlaceholder.PREFIXGLOBALMUTE + " " + SMPPlugin.getInstance().getConfig().getString("Globalmute.Diasable"));
            }
        }
        return enabled;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (this.enabled) {
            if (!e.getPlayer().hasPermission("yvtils.smp.bypass.mutechat")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(MessagePlaceholder.PREFIXGLOBALMUTE + " Globalmute is activated!");
            }
        }
    }
}
