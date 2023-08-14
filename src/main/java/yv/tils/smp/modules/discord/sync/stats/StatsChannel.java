package yv.tils.smp.modules.discord.sync.stats;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import org.bukkit.Bukkit;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.modules.discord.BotManager.BotStartStop;
import yv.tils.smp.utils.configs.discord.DiscordConfigManager;

import java.util.Collections;
import java.util.List;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class StatsChannel {

    public void createChannel() {

        //new DiscordConfigManager().StatsWriter(ChannelID, Guild);
        new DiscordConfigManager().StatsWriter("", "");

        List<Guild> guilds = BotStartStop.getInstance().jda.getGuilds();

        System.out.println("-------------------");
        System.out.println(guilds);
        System.out.println("-------------------");

        String ServerVersionLong = Bukkit.getServer().getVersion();
        String[] Version = ServerVersionLong.split(": ");
        String[] Ver = Version[1].split("[)]");

        String version = Ver[0];

        for (int i = 0; i < guilds.size(); i++) {
            if (SMPPlugin.getInstance().getConfig().getString("ServerIP") != null) {
                guilds.get(i).createVoiceChannel("Server IP: " + SMPPlugin.getInstance().getConfig().getString("ServerIP"))
                        .setUserlimit(0)
                        .addPermissionOverride(guilds.get(i).getPublicRole(), null, Collections.singleton(Permission.VOICE_CONNECT))
                        .queue(voiceChannel -> {
                            System.out.println("ID for voice channel: " + voiceChannel.getId());
                            SMPPlugin.getInstance().serveripID.add(voiceChannel.getId());

                            getID(voiceChannel.getIdLong());
                });
            }

            guilds.get(i).createVoiceChannel("Version: " + version)
                    .setUserlimit(0)
                    .addPermissionOverride(guilds.get(i).getPublicRole(), null, Collections.singleton(Permission.VOICE_CONNECT))
                    .queue(voiceChannel -> {
                        System.out.println("ID for voice channel: " + voiceChannel.getId());
                        SMPPlugin.getInstance().serverversionID.add(voiceChannel.getId());

                        getID(voiceChannel.getIdLong());
            });

            guilds.get(i).createVoiceChannel("Online Players: " + "0")
                    .setUserlimit(0)
                    .addPermissionOverride(guilds.get(i).getPublicRole(), null, Collections.singleton(Permission.VOICE_CONNECT))
                    .queue(voiceChannel -> {
                        System.out.println("ID for voice channel: " + voiceChannel.getId());
                        SMPPlugin.getInstance().playercountID.add(voiceChannel.getId());

                        getID(voiceChannel.getIdLong());
            });
        }
    }

    public void editChannel() {
        List<Guild> guilds = BotStartStop.getInstance().jda.getGuilds();

        for (int i = 0; i < guilds.size(); i++) {
            VoiceChannel serverip = guilds.get(i).getVoiceChannelById(SMPPlugin.getInstance().serveripID.get(i));
            VoiceChannel serverversion = guilds.get(i).getVoiceChannelById(SMPPlugin.getInstance().serverversionID.get(i));
            VoiceChannel onlineplayers = guilds.get(i).getVoiceChannelById(SMPPlugin.getInstance().playercountID.get(i));

            serverip.getManager().setName("Server IP: " + "string").queue();
            serverversion.getManager().setName("Version: " + "string").queue();
            onlineplayers.getManager().setName("Online Players: " + "integer").queue();
        }
    }
    public void deleteChannel() {
        List<Guild> guilds = BotStartStop.getInstance().jda.getGuilds();

        System.out.println(guilds.size());
        System.out.println(SMPPlugin.getInstance().serveripID.size());
        System.out.println(SMPPlugin.getInstance().serverversionID.size());
        System.out.println(SMPPlugin.getInstance().playercountID.size());

        for (int i = 0; i < guilds.size(); i++) {
            System.out.println(i + " <---------------------------------");
            try {
                VoiceChannel serverip = guilds.get(i).getVoiceChannelById(SMPPlugin.getInstance().serveripID.get(i));
                serverip.getManager().setName("Server IP: " + "null").queue();
            }catch (IndexOutOfBoundsException ignored) {}

            try {
                VoiceChannel serverversion = guilds.get(i).getVoiceChannelById(SMPPlugin.getInstance().serverversionID.get(i));
                serverversion.getManager().setName("Version: " + "null").queue();
            }catch (IndexOutOfBoundsException ignored) {}

            try {
                VoiceChannel onlineplayers = guilds.get(i).getVoiceChannelById(SMPPlugin.getInstance().playercountID.get(i));
                onlineplayers.getManager().setName("Online Players: " + "null").queue();
            }catch (IndexOutOfBoundsException ignored) {}
        }
    }

    private void getID(Long ID) {

        new ConsoleLog(ID.toString());

        new ConsoleLog(SMPPlugin.getInstance().serveripID.toString());
        new ConsoleLog(SMPPlugin.getInstance().serverversionID.toString());
        new ConsoleLog(SMPPlugin.getInstance().playercountID.toString());

        System.out.println("***********************");
        System.out.println(SMPPlugin.getInstance().serveripID);
        System.out.println(SMPPlugin.getInstance().serverversionID);
        System.out.println(SMPPlugin.getInstance().playercountID);
        System.out.println("***********************");
    }
}