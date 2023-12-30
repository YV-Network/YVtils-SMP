package yv.tils.smp.mods.discord.CommandManager;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;
import org.bukkit.Bukkit;
import yv.tils.smp.YVtils;
import yv.tils.smp.mods.discord.EmbedManager.commands.ServerInfoEmbed;
import yv.tils.smp.mods.discord.EmbedManager.whitelist.discord.ForceRemove;
import yv.tils.smp.mods.discord.whitelist.AccountCheck;
import yv.tils.smp.mods.discord.whitelist.ForceAdd;

import java.io.File;

/**
 * @version 4.6.8
 * @since 4.6.8.1
 */
public class CommandHandler extends ListenerAdapter {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        String command = e.getName();
        String args = e.getSubcommandName();

        switch (command) {
            case "mcinfo" -> {
                File serverIcon = new File("./server-icon.png");

                if (serverIcon.exists()) {
                    e.reply("").setEmbeds(new ServerInfoEmbed().Embed(e.getUser()).build()).setEphemeral(true).addFiles(FileUpload.fromData(serverIcon, "server-icon.png")).queue();
                } else {
                    e.reply("").setEmbeds(new ServerInfoEmbed().Embed(e.getUser()).build()).setEphemeral(true).queue();
                }
            }
            case "whitelist" -> {
                switch (args) {
                    case "forceadd" -> {
                        try {
                            e.reply("").setEmbeds(new ForceAdd().onMessageReceived(e.getOption("mc_name").getAsString(), e.getOption("dc_name").getAsMember(), e.getMember(), e.getGuild()).build()).setEphemeral(true).queue();
                        } catch (NullPointerException ignored) {
                            e.reply("").setEmbeds(new ForceAdd().onMessageReceived(e.getOption("mc_name").getAsString(), null, e.getMember(), e.getGuild()).build()).setEphemeral(true).queue();
                        }
                    }
                    case "forceremove" -> {
                        int site;
                        int maxSite;
                        try {
                            site = e.getOption("site").getAsInt();
                            maxSite = (YVtils.getInstance().whitelistManager.size() - 1) / 25 + 1;
                            if (site > maxSite) {
                                site = 1;
                            }
                        } catch (NullPointerException ignored) {
                            site = 1;
                            maxSite = 1;
                        }

                        e.reply("").setEmbeds(new ForceRemove().Embed((YVtils.getInstance().whitelistManager.size() - 1), Bukkit.hasWhitelist(), site).build()).addActionRow(new yv.tils.smp.mods.discord.whitelist.ForceRemove().createMenu(site).build()).setEphemeral(true).queue();
                    }
                    case "check" ->
                            e.reply("").setEmbeds(new AccountCheck().WhitelistCheck(e.getOption("name").getAsString()).build()).setEphemeral(true).queue();
                }
            }
        }
    }
}