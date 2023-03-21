package yv.tils.smp.modules.discord.CommandManager;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class CommandRegister extends ListenerAdapter {

    public void onReady(ReadyEvent e) {
        List<CommandData> commandData = new ArrayList<>();
        ServerInfoCommand(commandData);
        HelpCommand(commandData);
        WhitelistCommand(commandData);
        e.getJDA().updateCommands().addCommands(commandData).queue();
    }

    private void ServerInfoCommand(List<CommandData> commandData) {
        commandData.add(Commands.slash("mcinfo", "Sends a helpful overview about the Minecraft Server")
                .setGuildOnly(true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
    }

    private void HelpCommand(List<CommandData> commandData) {
        //Ingame
        List<Command.Choice> ingamehelplist = new ArrayList<>();
        ingamehelplist.add(new Command.Choice("CCR", "CCR"));
        ingamehelplist.add(new Command.Choice("Sit", "SIT"));
        OptionData ingamehelp = new OptionData(OptionType.STRING, "ingame", "Send an helpful overview about Ingame Functions", true).addChoices(ingamehelplist);

        //Discord
        List<Command.Choice> discordhelplist = new ArrayList<>();
        discordhelplist.add(new Command.Choice("whitelist", "WHITELIST"));
        discordhelplist.add(new Command.Choice("commands", "COMMANDS"));
        OptionData discordhelp = new OptionData(OptionType.STRING, "discord", "Send an helpful overview about Discord Bot Functions", true).addChoices(discordhelplist);

        commandData.add(Commands.slash("help", "Sends a helpful overview about Features")
                .addOptions(ingamehelp, discordhelp)
                .setGuildOnly(true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
    }

    private void WhitelistCommand(List<CommandData> commandData) {
        SubcommandData subCommandData1 = new SubcommandData("forceadd", "Let you add Minecraft Accounts to the Whitelist. You can link an User Account, if you want to");
        SubcommandData subCommandData2 = new SubcommandData("remove", "Let you remove players from the whitelist.");
        SubcommandData subCommandData3 = new SubcommandData("check", "Let you check, if a User has whitelisted an Account or with wich User, an MC Account is linked.");

        List<OptionData> options1 = new ArrayList<>();
        List<OptionData> options2 = new ArrayList<>();
        List<OptionData> options3 = new ArrayList<>();

        //ForceWhitelistAdd
        options1.add(new OptionData(OptionType.STRING, "mc_name", "Enter a Minecraft Ingame Name to whitelist this Name.", true));
        options1.add(new OptionData(OptionType.USER, "dc_name", "When you want to link an User Account to an MC Account, select here the Account of the User", false));

        //WhitelistRemove
        options2.add(new OptionData(OptionType.STRING, "remove", "When executed, sends an List with every whitelisted Player, select one and remove them.", true));

        //WhitelistCheck
        List<Command.Choice> choice3 = new ArrayList<>();
        choice3.add(new Command.Choice("Name", "CCR"));
        options3.add(new OptionData(OptionType.STRING, "check", "Send an helpful overview about Ingame Functions", true).addChoices(choice3));


        commandData.add(Commands.slash("whitelist", "Sends a Whitelist Request to the Minecraft Server")
                .addSubcommands(subCommandData1.addOptions(options1))
                .addSubcommands(subCommandData2.addOptions(options2))
                .addSubcommands(subCommandData3.addOptions(options3))
                .setGuildOnly(true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)));
    }
}
