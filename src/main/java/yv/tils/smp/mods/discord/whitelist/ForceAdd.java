package yv.tils.smp.mods.discord.whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;
import yv.tils.smp.YVtils;
import yv.tils.smp.mods.discord.EmbedManager.whitelist.*;
import yv.tils.smp.placeholder.Prefix;
import yv.tils.smp.utils.configs.discord.DiscordConfigManager;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;
import yv.tils.smp.utils.internalapi.StringReplacer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 4.6.8.1
 * @since 4.6.8
 */
public class ForceAdd {

    public EmbedBuilder onMessageReceived(String mc, Member dc, Member exec, Guild guild) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(mc);
        String userID = "~" + mc;
        String userName = "~" + mc;

        try {
            userID = dc.getUser().getId();
            userName = dc.getUser().getGlobalName();
        } catch (NullPointerException ignored) {
        }

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
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            int statusCode = http.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                if (new ImportWhitelist().reader(userID, null, null).contains(userID)) {

                    List<String> whitelist = new ImportWhitelist().reader(userID, null, null);

                    OfflinePlayer playerWhitelistRemove = Bukkit.getOfflinePlayer(whitelist.get(1));

                    new BukkitRunnable() {
                        public void run() {
                            playerWhitelistRemove.setWhitelisted(false);
                        }
                    }.runTask(YVtils.getInstance());

                    whitelistRemove(userID, playerWhitelistRemove.getName(), playerWhitelistRemove.getUniqueId().toString());

                    List<String> list1 = new ArrayList<>();
                    List<String> list2 = new ArrayList<>();
                    list1.add("DISCORDUSER");
                    list2.add(exec.getUser().getGlobalName());
                    list1.add("DCNAME");
                    list2.add(dc.getUser().getGlobalName());
                    list1.add("OLDNAME");
                    list2.add(whitelist.get(1));
                    list1.add("NEWNAME");
                    list2.add(mc);

                    //Account Changed
                    if (dc == null) {
                        new BukkitRunnable() {
                            public void run() {
                                player.setWhitelisted(true);
                            }
                        }.runTask(YVtils.getInstance());
                        YVtils.getInstance().whitelistManager.add(userID + "," + player.getName() + "," + player.getUniqueId());
                        new DiscordConfigManager().LinkedWriter(userID, mc + " " + player.getUniqueId());
                    } else {
                        try {
                            try {
                                String role = new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role");
                                role = role.replace(" ", "");
                                String[] roles = role.split(",");

                                try {
                                    for (String s : roles) {
                                        guild.addRoleToMember(dc, guild.getRoleById(s)).queue();
                                    }
                                } catch (NumberFormatException ignored) {
                                }

                                new BukkitRunnable() {
                                    public void run() {
                                        player.setWhitelisted(true);
                                    }
                                }.runTask(YVtils.getInstance());
                                YVtils.getInstance().whitelistManager.add(userID + "," + player.getName() + "," + player.getUniqueId());
                                new DiscordConfigManager().LinkedWriter(userID, mc + " " + player.getUniqueId());
                            } catch (HierarchyException ignored) {
                                return new RoleHierarchyError().Embed(new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role"), guild);
                            }
                        } catch (IllegalArgumentException ignored) {
                        }
                    }
                    Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(Prefix.PREFIXDC + " §f" + LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_CMD_REGISTERED_CHANGE), list1, list2));
                    return new yv.tils.smp.mods.discord.EmbedManager.whitelist.discord.ForceAdd().Replace(userName, whitelist.get(1), mc);
                } else {
                    List<String> list1 = new ArrayList<>();
                    List<String> list2 = new ArrayList<>();
                    list1.add("DISCORDUSER");
                    list2.add(exec.getUser().getGlobalName());
                    list1.add("DCNAME");
                    list2.add(userName);
                    list1.add("MCNAME");
                    list2.add(mc);

                    //Account Added
                    if (dc == null) {
                        new BukkitRunnable() {
                            public void run() {
                                player.setWhitelisted(true);
                            }
                        }.runTask(YVtils.getInstance());
                        YVtils.getInstance().whitelistManager.add(userID + "," + player.getName() + "," + player.getUniqueId());
                        new DiscordConfigManager().LinkedWriter(userID, mc + " " + player.getUniqueId());
                    } else {
                        try {
                            try {
                                String role = new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role");
                                role = role.replace(" ", "");
                                String[] roles = role.split(",");

                                try {
                                    for (String s : roles) {
                                        guild.addRoleToMember(dc, guild.getRoleById(s)).queue();
                                    }
                                } catch (NumberFormatException ignored) {
                                }

                                new BukkitRunnable() {
                                    public void run() {
                                        player.setWhitelisted(true);
                                    }
                                }.runTask(YVtils.getInstance());
                                YVtils.getInstance().whitelistManager.add(userID + "," + player.getName() + "," + player.getUniqueId());
                                new DiscordConfigManager().LinkedWriter(userID, mc + " " + player.getUniqueId());
                            } catch (HierarchyException ignored) {
                                return new RoleHierarchyError().Embed(new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role"), guild);
                            }
                        } catch (IllegalArgumentException ignored) {
                        }
                    }
                    Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(Prefix.PREFIXDC + " §f" + LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_CMD_REGISTERED_ADD), list1, list2));
                    return new yv.tils.smp.mods.discord.EmbedManager.whitelist.discord.ForceAdd().Embed(mc, userName);
                }
            } else if (statusCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                //Account not found
                return new AccountNotFound().Embed(mc);
            } else {
                //Server Error
                return new AccountCheckError().Embed(mc);
            }
        } catch (IOException ignored) {
        }
        return null;
    }

    private void whitelistAdd(String dc, String mc, String uuid) {
        YVtils.getInstance().whitelistManager.add(dc + "," + mc + "," + uuid);
    }

    private void whitelistRemove(String dc, String mc, String uuid) {
        YVtils.getInstance().whitelistManager.remove(dc + "," + mc + "," + uuid);
    }
}