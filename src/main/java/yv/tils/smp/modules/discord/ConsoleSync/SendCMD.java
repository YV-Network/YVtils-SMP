package yv.tils.smp.modules.discord.ConsoleSync;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import yv.tils.smp.SMPPlugin;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class SendCMD extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getChannelType().compareTo(ChannelType.TEXT) != 0) return;

        TextChannel channel = e.getChannel().asTextChannel();

        if (!channel.getId().equals("1097159318387838986")) return;
        if(e.getAuthor().isBot()) return;

        Message msg = e.getMessage();
        String contentDisplay = msg.getContentDisplay();

        contentDisplay = contentDisplay.replace("/", "");

        String finalContentDisplay = contentDisplay;
        Bukkit.getScheduler().runTask(SMPPlugin.getInstance(), () ->
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), finalContentDisplay));
    }
}