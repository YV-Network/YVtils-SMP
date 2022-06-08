package yv.tils.smp.modules.discord;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import yv.tils.smp.LanguageSystem.LanguageFile;
import yv.tils.smp.LanguageSystem.LanguageMessage;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.placeholder.StringReplacer;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @since 4.6.6
 * @version 4.6.6
 */
public class WhitelistMessageGetter extends ListenerAdapter {

    //DiscordName+Tag: Minecraft Username -> Example: WolfiiYV#3204: WolfiiYV

    File file1 = new File(SMPPlugin.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration mc_dcbridge = YamlConfiguration.loadConfiguration(file1);
    File file3 = new File(SMPPlugin.getInstance().getDataFolder(), "WhitelistedDiscordPlayers.yml");
    YamlConfiguration whitelistlogfile = YamlConfiguration.loadConfiguration(file3);

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        TextChannel channel = e.getTextChannel();

        if (e.getAuthor().isBot()) {
            return;
        }

        if (e.getChannel().getId().equals(mc_dcbridge.getString("WhitelistChannelID"))) {
            Player player = Bukkit.getServer().getPlayer(e.getMessage().getContentRaw());
            OfflinePlayer player1 = Bukkit.getOfflinePlayer(e.getMessage().getContentRaw());
            String name = e.getMessage().getContentRaw();
            String MessageId = e.getMessageId();

            if (!name.matches("[a-zA-Z0-9_]+")) {
                channel.deleteMessageById(MessageId).queue();
                channel.sendMessageEmbeds(new BuildEmbeds().namehasunallowedcharacters(e.getMessage().getContentRaw(),e.getGuild()).build()).complete().delete().queueAfter(5, TimeUnit.SECONDS);
                return;
            }

            try {
                URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                int statusCode = http.getResponseCode();
                if (statusCode == 200) {
                    if (whitelistlogfile.get(e.getMember().getUser().getAsTag()) != null) {
                        String configname = e.getMember().getUser().getAsTag();
                        String configname_remove = (String) whitelistlogfile.get(configname);
                        String[] liststring = configname_remove.split(" ");
                        OfflinePlayer playerwhitelistremove = Bukkit.getOfflinePlayer(liststring[0]);
                        playerwhitelistremove.setWhitelisted(false);

                        List<String> list1 = new ArrayList();
                        List<String> list2 = new ArrayList();
                        list1.add("DISCORDUSER");
                        list2.add(e.getMember().getUser().getAsTag());
                        list1.add("OLDNAME");
                        list2.add(liststring[0]);
                        list1.add("NEWNAME");
                        list2.add(e.getMessage().getContentRaw());

                        Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(MessagePlaceholder.PREFIXDC + " §f" + LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_REGISTERED_NAME_CHANGE), list1, list2));
                        channel.sendMessageEmbeds(new BuildEmbeds().namechanged(liststring[0], e.getMessage().getContentRaw(), e.getGuild()).build()).complete().delete().queueAfter(5, TimeUnit.SECONDS);
                    }else {

                        List<String> list1 = new ArrayList();
                        List<String> list2 = new ArrayList();
                        list1.add("DISCORDUSER");
                        list2.add(e.getMember().getUser().getAsTag());
                        list1.add("NAME");
                        list2.add(e.getMessage().getContentRaw());

                        Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(MessagePlaceholder.PREFIXDC + " §f" + LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_REGISTERED_NAME_ADD), list1, list2));
                        channel.sendMessageEmbeds(new BuildEmbeds().namewhitelisted(e.getMessage().getContentRaw(), e.getGuild()).build()).complete().delete().queueAfter(5, TimeUnit.SECONDS);
                    }
                    channel.deleteMessageById(MessageId).queue();
                    player1.setWhitelisted(true);
                    whitelistlogfile.set(e.getMember().getUser().getAsTag() ,e.getMessage().getContentRaw() + " " + player1.getUniqueId());

                    try {
                        whitelistlogfile.save(file3);
                    } catch (IOException ev) {
                        System.out.println("---4---");
                        Bukkit.getConsoleSender().sendMessage("File Save Error");
                        System.out.println("---4---");
                    }
                }else if (statusCode == 204) {

                    List<String> list1 = new ArrayList();
                    List<String> list2 = new ArrayList();
                    list1.add("DISCORDUSER");
                    list2.add(e.getMember().getUser().getAsTag());
                    list1.add("NAME");
                    list2.add(e.getMessage().getContentRaw());

                    channel.deleteMessageById(MessageId).queue();
                    Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_REGISTERED_NAME_WRONG), list1, list2));
                    channel.sendMessageEmbeds(new BuildEmbeds().namenotexisting(e.getMessage().getContentRaw(),e.getGuild()).build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
                }else {

                    List<String> list1 = new ArrayList();
                    List<String> list2 = new ArrayList();
                    list1.add("DISCORDUSER");
                    list2.add(e.getMember().getUser().getAsTag());
                    list1.add("NAME");
                    list2.add(e.getMessage().getContentRaw());

                    channel.deleteMessageById(MessageId).queue();
                    Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_REGISTERED_NAME_SERVERERROR_CHECK_INPUT), list1, list2));
                    channel.sendMessageEmbeds(new BuildEmbeds().namecheckservererror(e.getMessage().getContentRaw(),e.getGuild()).build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
                }} catch (IOException ignored) {}}}}