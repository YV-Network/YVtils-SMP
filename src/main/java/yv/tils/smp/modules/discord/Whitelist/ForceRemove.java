package yv.tils.smp.modules.discord.Whitelist;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import yv.tils.smp.SMPPlugin;
import yv.tils.smp.logger.ConsoleLog;
import yv.tils.smp.modules.discord.BotManager.BotStartStop;
import yv.tils.smp.modules.discord.EmbedManager.whitelist.RoleHierarchyError;
import yv.tils.smp.placeholder.MessagePlaceholder;
import yv.tils.smp.placeholder.StringReplacer;
import yv.tils.smp.utils.configs.discord.DiscordConfigManager;
import yv.tils.smp.utils.configs.language.LanguageFile;
import yv.tils.smp.utils.configs.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 4.6.8
 * @since 4.6.8
 */
public class ForceRemove extends ListenerAdapter {

    public StringSelectMenu.Builder createMenu() {

        List<String> list = SMPPlugin.getInstance().WhitelistManager;

        if (list.size() <= 1) {
            return StringSelectMenu.create("players").setPlaceholder(LanguageFile.getMessage(LanguageMessage.WHITELIST_EMPTY)).setDisabled(true).addOption("test", "test");
        }

        List<SelectOption> options = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            //options.add(SelectOption.of(display, value));
            options.add(SelectOption.of(list.get(i), list.get(i)));
        }

        return StringSelectMenu.create("players").setPlaceholder("Discord Tag,Minecraft Name, UUID")
                .addOptions(options);
    }

    public void onStringSelectInteraction(StringSelectInteractionEvent e) {

        List<String> list = SMPPlugin.getInstance().WhitelistManager;

        if (e.getInteraction().getValues().isEmpty()) return;
        else {

            Guild guild = e.getGuild();

            String values = e.getValues().toString();

            values = values.replace("[", "");
            values = values.replace("]", "");

            String[] args = values.split(",");

            OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);

            list.remove(args[0] + "," + args[1] + "," + args[2]);
            player.setWhitelisted(false);
            new DiscordConfigManager().LinkedWriter(args[0], null);

            User user = BotStartStop.getInstance().jda.getUserByTag(args[0]);

            new ConsoleLog(user + " " + args[0]);

            try {
                try {
                    guild.removeRoleFromMember(user, guild.getRoleById(new DiscordConfigManager().ConfigRequest().getLong("WhitelistFeature.Role"))).queue();
                }catch (HierarchyException ignored) {
                    e.reply("").setEmbeds(new RoleHierarchyError().Embed(guild.getRoleById(new DiscordConfigManager().ConfigRequest().getLong("WhitelistFeature.Role"))).build()).setEphemeral(true).queue();
                }
            }catch (IllegalArgumentException ignored) {}


            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("DISCORDUSER");
            list2.add(e.getMember().getUser().getAsTag());
            list1.add("MCNAME");
            list2.add(args[1]);
            list1.add("DCNAME");
            list2.add(args[0]);

            Bukkit.getConsoleSender().sendMessage(new StringReplacer().ListReplacer(MessagePlaceholder.PREFIXDC + " §f" + LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_CMD_REGISTERED_REMOVE), list1, list2));
            e.editMessageEmbeds(new yv.tils.smp.modules.discord.EmbedManager.whitelist.discord.ForceRemove().EmbedRemoved((list.size()-1), Bukkit.hasWhitelist(), args).build()).setActionRow(createMenu().build()).queue();
        }
    }
}