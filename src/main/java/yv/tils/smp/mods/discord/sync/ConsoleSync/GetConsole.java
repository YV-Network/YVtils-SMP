package yv.tils.smp.mods.discord.sync.ConsoleSync;

import net.dv8tion.jda.api.JDA;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import yv.tils.smp.YVtils;
import yv.tils.smp.utils.configs.discord.DiscordConfigManager;
import yv.tils.smp.utils.configs.language.LanguageFile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class GetConsole extends AbstractAppender {
    private final YVtils plugin;
    private final JDA jda;
    private String messages = "";

    public GetConsole(YVtils plugin, JDA jda) {
        super("MyLogAppender" + System.currentTimeMillis(), null, null);
        this.plugin = plugin;
        this.jda = jda;
        this.start();
    }

    public void append(LogEvent e) {
        String message = e.getMessage().getFormattedMessage();
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
                        GetConsole.this.messages = GetConsole.this.messages.replaceAll("\u007F", "&");
                        GetConsole.this.messages = ChatColor.translateAlternateColorCodes('&', GetConsole.this.messages);
                        GetConsole.this.messages = ChatColor.stripColor(GetConsole.this.messages);
                        if (GetConsole.this.messages.length() > 2000) {
                            String messageTooLong = "\n\n" +
                                    LanguageFile.DirectFormatter("This message has exceeded the discord message limit (2000 characters) so the rest has been cut out. To see it completely please check the console itself!",
                                            "Dieser Log hat das Zeichenlimit von Discord überschritten (2000 Zeichen), weswegen der Rest abgeschnitten wurde. Um den kompletten Log zu sehen, schaue bitte in die Konsole!");
                            GetConsole.this.messages = GetConsole.this.messages.substring(0, 1999 - messageTooLong.length() - 6);
                            GetConsole console = GetConsole.this;
                            console.messages = console.messages + messageTooLong;
                        }

                        String channel = new DiscordConfigManager().ConfigRequest().getString("ConsoleSync.Channel");

                        try {
                            GetConsole.this.jda.getTextChannelById(channel).sendMessage("```" + GetConsole.this.messages + "```").queue();
                        } catch (NumberFormatException ignored) {
                            Bukkit.getLogger().severe("[YVtils-SMP -> ConsoleSync] " +
                                    LanguageFile.DirectFormatter("Invalid channel ID: '" + channel + "'! Make sure to put a valid channel ID in the config file or disable this feature! (plugins/YVtils-SMP/Discord/config.yml/ConsoleSync)",
                                            "Ungültige Kanal ID: '" + channel + "'! Kontrolliere/Korrigiere noch mal die Kanal ID in der Config oder deaktiviere diese Funktion! (plugins/YVtils-SMP/Discord/config.yml/ConsoleSync)"));
                            this.cancel();
                        }
                    }
                } catch (NullPointerException ignored) {
                }

                GetConsole.this.messages = "";
            }
        }).runTaskTimer(this.plugin, 0L, 200L);
    }
}
