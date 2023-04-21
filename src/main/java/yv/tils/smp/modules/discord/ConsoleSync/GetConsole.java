package yv.tils.smp.modules.discord.ConsoleSync;

import net.dv8tion.jda.api.JDA;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import yv.tils.smp.SMPPlugin;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class GetConsole extends AbstractAppender {
    private final SMPPlugin plugin;
    private String messages = "";
    private final JDA jda;

    public GetConsole(SMPPlugin plugin, JDA jda) {
        super("MyLogAppender" + System.currentTimeMillis(), (Filter)null, (Layout)null);
        this.plugin = plugin;
        this.jda = jda;
        this.start();
    }

    public void append(LogEvent e) {


        System.out.println(e.getMessage().toString() + " 1");
        System.out.println(e.getMessage().getFormattedMessage().toString() + " 2");
        System.out.println(e.getMessage().getFormat().toString() + " 3");

        String message = e.getMessage().toString();

        System.out.println(message.contains("&"));
        System.out.println(message.contains("§"));

        message = ChatColor.translateAlternateColorCodes('&', message);
        message = ChatColor.stripColor(message);


        System.out.println(message.toString()+ " -1");


        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        message = "[" + sdf1.format(timestamp) + " " + e.getLevel().toString() + "]: " + message + "\n";
        this.messages = this.messages + message;
    }

    public void sendMessages() {
        (new BukkitRunnable() {
            public void run() {
                try {
                    if (GetConsole.this.messages.length() != 0) {
                        GetConsole.this.messages = GetConsole.this.messages.replaceAll("\u001b\\[[;\\d]*m", "");
                        if (GetConsole.this.messages.length() > 2000) {
                            String messageTooLong = "\n\nThis message has exceeded the discord message limit (2000 characters) so the rest has been cut out. To see it completely please check the console itself!";
                            GetConsole.this.messages = GetConsole.this.messages.substring(0, 1999 - messageTooLong.length() - 6);
                            GetConsole console = GetConsole.this;
                            console.messages = console.messages + messageTooLong;
                        }

                        //String channel = new DiscordConfigManager().ConfigRequest().getString("");

                        String channel = "1097159318387838986";

                        try {
                            GetConsole.this.jda.getTextChannelById(channel).sendMessage("```" + GetConsole.this.messages + "```").queue();
                        } catch (NumberFormatException exce) {
                            Bukkit.getLogger().severe("[DiscordServerConsole] Invalid channel ID '" + channel + "'! Make sure to put a valid channel ID in the config file! Without this the plugin won't work properly! If you're sure you've done this correctly, please contact plugin support on the Discord server: https://discord.gg/d3ac2tJ . Disabling plugin...");
                            this.cancel();
                        }
                    }
                } catch (NullPointerException ignored) {
                }

                GetConsole.this.messages = "";
            }
        }).runTaskTimer(this.plugin, 0L, 20L);
    }
}
