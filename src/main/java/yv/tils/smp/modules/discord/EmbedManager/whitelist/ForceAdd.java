package yv.tils.smp.modules.discord.EmbedManager.whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.modules.discord.EmbedManager.whitelist.Embed.*;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.configs.discord.DiscordConfigManager;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class ForceAdd {

    YamlConfiguration linkedRequest = new DiscordConfigManager().LinkedRequest();

    public EmbedBuilder onMessageReceived(String mc, Member dc, Member exec) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(mc);
        String dc_tag = mc + "#0000";

        try {
            dc_tag = dc.getUser().getAsTag();
        }catch (NullPointerException ignored) {}

        dc_tag = dc_tag.replace(",", "");
        dc_tag = dc_tag.replace("[", "");
        dc_tag = dc_tag.replace("]", "");

        if (!mc.matches("[a-zA-Z0-9_]+")) {
            //Account can't exist
            return new AccountCantExist().Embed(mc);
        }

        if (player.isWhitelisted()) {
            //Already Whitelisted
            return new AccountAlreadyListed().Embed(mc);
        }

        try {
            //Searching for Minecraft Account
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + mc);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            int statusCode = http.getResponseCode();
            if (statusCode == 200) {
                if (linkedRequest.get(dc_tag) != null) {
                    String configname_remove = (String) linkedRequest.get(dc_tag);
                    String[] liststring = configname_remove.split(" ");
                    OfflinePlayer playerwhitelistremove = Bukkit.getOfflinePlayer(liststring[0]);
                    playerwhitelistremove.setWhitelisted(false);
                    SMPPlugin.getInstance().WhitelistManager.remove(dc_tag + "," + playerwhitelistremove.getName() + "," + playerwhitelistremove.getUniqueId());

                    List<String> list1 = new ArrayList();
                    List<String> list2 = new ArrayList();
                    list1.add("DISCORDUSER");
                    list2.add(exec.getUser().getAsTag());
                    list1.add("DCNAME");
                    list2.add(dc_tag);
                    list1.add("OLDNAME");
                    list2.add(liststring[0]);
                    list1.add("NEWNAME");
                    list2.add(mc);

                    //Account Changed
                    player.setWhitelisted(true);
                    SMPPlugin.getInstance().WhitelistManager.add(dc_tag + "," + player.getName() + "," + player.getUniqueId());
                    new DiscordConfigManager().LinkedWriter(dc_tag, mc+ " " + player.getUniqueId());
                    Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(MessagePlaceholder.PREFIXDC + " §f" + LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_CMD_REGISTERED_CHANGE), list1, list2));
                    return new yv.tils.smp.modules.discord.EmbedManager.whitelist.Embed.discord.ForceAdd().Replace(dc_tag, liststring[0], mc);
                }else {

                    List<String> list1 = new ArrayList();
                    List<String> list2 = new ArrayList();
                    list1.add("DISCORDUSER");
                    list2.add(exec.getUser().getAsTag());
                    list1.add("DCNAME");
                    list2.add(dc_tag);
                    list1.add("MCNAME");
                    list2.add(mc);

                    //Account Added
                    player.setWhitelisted(true);
                    SMPPlugin.getInstance().WhitelistManager.add(dc_tag + "," + player.getName() + "," + player.getUniqueId());
                    new DiscordConfigManager().LinkedWriter(dc_tag, mc+ " " + player.getUniqueId());
                    Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(MessagePlaceholder.PREFIXDC + " §f" + LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_CMD_REGISTERED_ADD), list1, list2));
                    return new yv.tils.smp.modules.discord.EmbedManager.whitelist.Embed.discord.ForceAdd().Embed(mc, dc_tag);
                }
            }else if (statusCode == 400) {

                List<String> list1 = new ArrayList();
                List<String> list2 = new ArrayList();
                list1.add("DISCORDUSER");
                list2.add(dc_tag);
                list1.add("NAME");
                list2.add(mc);

                //Account not found
                return new AccountNotFound().Embed(mc);
            }else {

                List<String> list1 = new ArrayList();
                List<String> list2 = new ArrayList();
                list1.add("DISCORDUSER");
                list2.add(dc_tag);
                list1.add("NAME");
                list2.add(mc);

                //Server Error
                return new AccountCheckError().Embed(mc);
            }} catch (IOException ignored) {}
        return null;
    }}