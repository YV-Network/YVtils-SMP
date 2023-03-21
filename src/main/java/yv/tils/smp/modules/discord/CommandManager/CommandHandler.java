package yv.tils.smp.modules.discord.CommandManager;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;
import yv.tils.smp.modules.discord.EmbedManager.commands.ServerInfoEmbed;

import java.io.File;

public class CommandHandler extends ListenerAdapter  {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        String command = e.getName();
        TextChannel textChannel = e.getChannel().asTextChannel();

        switch (command) {
            case "mcinfo" -> {
                File serverIcon = new File(".\\server-icon.png");
                if (serverIcon.exists()) {
                    e.reply("").setEmbeds(new ServerInfoEmbed().Embed(e.getUser()).build()).setEphemeral(true).addFiles(FileUpload.fromData(serverIcon, "server-icon.png")).queue();
                }else {
                    e.reply("").setEmbeds(new ServerInfoEmbed().Embed(e.getUser()).build()).setEphemeral(true).queue();
                }
            }
        }
    }
}
