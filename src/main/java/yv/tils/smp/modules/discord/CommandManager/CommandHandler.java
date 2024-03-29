package yv.tils.smp.modules.discord.CommandManager;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;
import org.bukkit.Bukkit;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.modules.discord.EmbedManager.commands.ServerInfoEmbed;
import yv.tils.smp.modules.discord.whitelist.AccountCheck;
import yv.tils.smp.modules.discord.whitelist.ForceAdd;
import yv.tils.smp.modules.discord.whitelist.ForceRemove;

import java.io.File;

/**
 * @version 4.6.8
 * @since 4.6.8.1
 */
public class CommandHandler extends ListenerAdapter  {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        String command = e.getName();
        String args = e.getSubcommandName();

        switch (command) {
            case "mcinfo" -> {
                File serverIcon = new File("./server-icon.png");

                if (serverIcon.exists()) {
                    e.reply("").setEmbeds(new ServerInfoEmbed().Embed(e.getUser()).build()).setEphemeral(true).addFiles(FileUpload.fromData(serverIcon, "server-icon.png")).queue();
                }else {
                    e.reply("").setEmbeds(new ServerInfoEmbed().Embed(e.getUser()).build()).setEphemeral(true).queue();
                }
            }
            case "whitelist" -> {
                switch (args) {
                    case "forceadd" -> {
                        try {
                            e.reply("").setEmbeds(new ForceAdd().onMessageReceived(e.getOption("mc_name").getAsString(), e.getOption("dc_name").getAsMember(), e.getMember(), e.getGuild()).build()).setEphemeral(true).queue();
                        }catch (NullPointerException ignored) {
                            e.reply("").setEmbeds(new ForceAdd().onMessageReceived(e.getOption("mc_name").getAsString(), null, e.getMember(), e.getGuild()).build()).setEphemeral(true).queue();
                        }
                    }
                    case "forceremove" -> {
                        int site;
                        int maxsite;
                        try {
                            site = e.getOption("site").getAsInt();
                            maxsite = (SMPPlugin.getInstance().WhitelistManager.size() - 1)/25+1;
                            if (site > maxsite) {
                             site = 1;
                            }
                        }catch (NullPointerException ignored) {
                            site = 1;
                            maxsite = 1;
                        }

                        e.reply("").setEmbeds(new yv.tils.smp.modules.discord.EmbedManager.whitelist.discord.ForceRemove().Embed((SMPPlugin.getInstance().WhitelistManager.size()-1), Bukkit.hasWhitelist(), site).build()).addActionRow(new ForceRemove().createMenu(site).build()).setEphemeral(true).queue();
                    }
                    case "check" -> {
                        e.reply("").setEmbeds(new AccountCheck().WhitelistCheck(e.getOption("name").getAsString()).build()).setEphemeral(true).queue();
                    }
                }
            }
        }
    }
}