package yv.tils.smp.modules.discord.Whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.modules.discord.EmbedManager.whitelist.*;
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

    public EmbedBuilder onMessageReceived(String mc, Member dc, Member exec, Guild guild) {
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
                if (new ImportWhitelist().reader(dc_tag, null, null).contains(dc_tag)) {

                    List<String> whitelist = new ImportWhitelist().reader(dc_tag, null, null);

                    OfflinePlayer playerwhitelistremove = Bukkit.getOfflinePlayer(whitelist.get(1));
                    playerwhitelistremove.setWhitelisted(false);
                    whitelistRemove(dc_tag, playerwhitelistremove.getName(), playerwhitelistremove.getUniqueId().toString());

                    List<String> list1 = new ArrayList();
                    List<String> list2 = new ArrayList();
                    list1.add("DISCORDUSER");
                    list2.add(exec.getUser().getAsTag());
                    list1.add("DCNAME");
                    list2.add(dc_tag);
                    list1.add("OLDNAME");
                    list2.add(whitelist.get(1));
                    list1.add("NEWNAME");
                    list2.add(mc);

                    //Account Changed
                    if (dc == null) {
                        player.setWhitelisted(true);
                        SMPPlugin.getInstance().WhitelistManager.add(dc_tag + "," + player.getName() + "," + player.getUniqueId());
                        new DiscordConfigManager().LinkedWriter(dc_tag, mc+ " " + player.getUniqueId());
                    }else {
                        try {
                            try {
                                String role = new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role");
                                role = role.replace(" ", "");
                                String[] roles = role.split(",");
                                for (int i = 0; i < roles.length; i++) {
                                    guild.addRoleToMember(dc, guild.getRoleById(roles[i])).queue();
                                }

                                player.setWhitelisted(true);
                                SMPPlugin.getInstance().WhitelistManager.add(dc_tag + "," + player.getName() + "," + player.getUniqueId());
                                new DiscordConfigManager().LinkedWriter(dc_tag, mc + " " + player.getUniqueId());
                            } catch (HierarchyException ignored) {
                                return new RoleHierarchyError().Embed(new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role"), guild);
                            }
                        } catch (IllegalArgumentException ignored) {
                        }
                    }
                    Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(MessagePlaceholder.PREFIXDC + " §f" + LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_CMD_REGISTERED_CHANGE), list1, list2));
                    return new yv.tils.smp.modules.discord.EmbedManager.whitelist.discord.ForceAdd().Replace(dc_tag, whitelist.get(1), mc);
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
                    if (dc == null) {
                        player.setWhitelisted(true);
                        SMPPlugin.getInstance().WhitelistManager.add(dc_tag + "," + player.getName() + "," + player.getUniqueId());
                        new DiscordConfigManager().LinkedWriter(dc_tag, mc+ " " + player.getUniqueId());
                    }else {
                        try {
                            try {
                                String role = new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role");
                                role = role.replace(" ", "");
                                String[] roles = role.split(",");
                                for (int i = 0; i < roles.length; i++) {
                                    guild.addRoleToMember(dc, guild.getRoleById(roles[i])).queue();
                                }

                                player.setWhitelisted(true);
                                SMPPlugin.getInstance().WhitelistManager.add(dc_tag + "," + player.getName() + "," + player.getUniqueId());
                                new DiscordConfigManager().LinkedWriter(dc_tag, mc + " " + player.getUniqueId());
                            } catch (HierarchyException ignored) {
                                return new RoleHierarchyError().Embed(new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role"), guild);
                            }
                        } catch (IllegalArgumentException ignored) {
                            System.out.println(ignored);
                        }
                    }
                    Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(MessagePlaceholder.PREFIXDC + " §f" + LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_CMD_REGISTERED_ADD), list1, list2));
                    return new yv.tils.smp.modules.discord.EmbedManager.whitelist.discord.ForceAdd().Embed(mc, dc_tag);
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
    }

    private void whitelistAdd(String dc, String mc, String uuid) {
        SMPPlugin.getInstance().WhitelistManager.add(dc + "," + mc + "," + uuid);
    }

    private void whitelistRemove(String dc, String mc, String uuid) {
        SMPPlugin.getInstance().WhitelistManager.remove(dc + "," + mc + "," + uuid);
    }}