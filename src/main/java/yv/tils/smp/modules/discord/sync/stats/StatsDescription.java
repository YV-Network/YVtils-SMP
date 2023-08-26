package yv.tils.smp.modules.discord.sync.stats;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.modules.discord.BotManager.BotStartStop;
import yv.tils.smp.utils.configs.discord.DiscordConfigManager;
import yv.tils.smp.utils.configs.language.LanguageFile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @version 4.6.8
 * @since 4.6.8.1
 */
public class StatsDescription {
    public void editDesc() {
        String ChannelID = new DiscordConfigManager().ConfigRequest().getString("ChatSync.Channel");
        List<Guild> guilds = BotStartStop.getInstance().jda.getGuilds();

        new BukkitRunnable() {
            public void run() {
                String ServerVersionLong = Bukkit.getServer().getVersion();
                String[] Version = ServerVersionLong.split(": ");
                String[] Ver = Version[1].split("[)]");

                String version = Ver[0];

                String serverip = SMPPlugin.getInstance().getConfig().getString("ServerIP");
                String onlineplayers = String.valueOf(Bukkit.getOnlinePlayers().size());

                SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                new ConsoleLog("Task running!");

                TextChannel channel = null;

                for (int i = 0; i < guilds.size(); i++) {
                    try {
                        channel = guilds.get(i).getTextChannelById(ChannelID);
                    } catch (NumberFormatException ignored) {
                        if (!(guilds.size() > i + 1)) {
                            Bukkit.getLogger().severe("[YVtils-SMP -> StatsDescription] " +
                                    LanguageFile.DirectFormatter("Invalid channel ID: '" + channel + "'! Make sure to put a valid channel ID in the config file or disable this feature! (plugins/YVtils-SMP/Discord/config.yml/ChatSync)",
                                            "Ungültige Kanal ID: '" + channel + "'! Kontrolliere/Korrigiere noch mal die Kanal ID in der Config oder deaktiviere diese Funktion! (plugins/YVtils-SMP/Discord/config.yml/ChatSync)"));
                            break;
                        }
                    }
                }

                if (channel == null) return;

                try {
                    if (serverip.equals("null")) {
                        channel.getManager().setTopic("Server IP: " + serverip + " Version: " + version + " Online Players: " + onlineplayers + " \nLast Updated: " + dateformat.format(timestamp)).queue();
                    }else {
                        channel.getManager().setTopic("Version: " + version + " Online Players: " + onlineplayers + " \nLast Updated: " + dateformat.format(timestamp)).queue();
                    }
                }catch (NullPointerException ignored) {
                    channel.getManager().setTopic("Version: " + version + " Online Players: " + onlineplayers + " \nLast Updated: " + dateformat.format(timestamp)).queue();
                }
            }
        }.runTaskTimer(SMPPlugin.getInstance(), 0L, 12000L);
    }
}